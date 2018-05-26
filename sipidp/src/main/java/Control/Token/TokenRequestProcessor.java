package Control.Token;

import Common.Exceptions.FrameworkCheckedException;
import Models.Client;
import Models.OpenIDConnectObject;
import Models.SharedIdentityObject;
import storage.Clients;
import storage.TokenStorage;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TokenRequestProcessor {

    private TokenRequestProcessor() {
    }

    public static JsonObject processRequest(final HttpServletRequest request) {
        final Map<String, String[]> parameterMap = request.getParameterMap();

        // Verify client authentication
        final String[] clientId = parameterMap.get("client_id");
        if (clientId == null || clientId.length < 1) {
            return createErrorResponse("Missing client grants");
        }

        final Client client;

        try {
            client = Clients.getClientOnId(clientId[0]);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse("Invalid client grants");
        }

        final String[] redirectUrl = parameterMap.get("redirect_url");
        if (redirectUrl == null || redirectUrl.length < 1) {
            return createErrorResponse("Missing client grants");
        }

        final String[] clientSecret = parameterMap.get("client_secret");
        if (clientSecret == null || clientSecret.length < 1) {
            return createErrorResponse("Missing client grants");
        }

        if (!(client.authenticate(clientSecret[0])
                && client.getRedirectUrl().equals(redirectUrl[0]))) {
            return createErrorResponse("Invalid client grants");
        }


        // Grant type - grant_type
        final String[] grant_types = parameterMap.get("grant_type");
        if (grant_types == null || grant_types.length < 1) {
            return createErrorResponse("Grant type not provided");
        }

        // This is token request
        if ("authorization_code".equals(grant_types[0])) {
            return processTokenRequest(parameterMap);
        }

        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Error", "Invalid grant type");

        return objectBuilder.build();
    }

    private static JsonObject processTokenRequest(final Map<String, String[]> parameterMap) {
        // Verify auth code
        final String[] codes = parameterMap.get("code");
        if (codes == null || codes.length < 1) {
            return createErrorResponse("Missing authorization code");
        }

        final String authorizationCode = codes[0];

        // Get token object by auth code
        final OpenIDConnectObject tokenObject;
        try {
            tokenObject = (OpenIDConnectObject) TokenStorage.getByAuthCode(authorizationCode);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Invalid grants");
        }

        // If valid store by access token
        try {
            TokenStorage.addByAccessToken(authorizationCode, tokenObject.getAccessToken(), tokenObject);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Failure to store token");
        }


        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("access_token", tokenObject.getAccessToken());
        objectBuilder.add("token_type", "Bearer");
        objectBuilder.add("id_token", tokenObject.getIdToken());

        if (tokenObject instanceof SharedIdentityObject) {
            final SharedIdentityObject sharedIdentityObject = (SharedIdentityObject) tokenObject;
            objectBuilder.add("shared_token", sharedIdentityObject.getSharedIdentityToken());
        }

        return objectBuilder.build();
    }

    private static JsonObject createErrorResponse(final String message) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Error", message);
        return objectBuilder.build();
    }

    private static JsonObject createErrorResponse(final FrameworkCheckedException ex, final String message) {
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Error", message);
        objectBuilder.add("Cause", ex.getCustomMessage());
        objectBuilder.add("Details", ex.getMessage());
        return objectBuilder.build();
    }

}
