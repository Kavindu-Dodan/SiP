package Control.Token;

import Common.Exceptions.FrameworkCheckedException;
import Models.OpenIDConnectObject;
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
        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        final Map<String, String[]> parameterMap = request.getParameterMap();

        // Grant type - grant_type
        final String[] grant_types = parameterMap.get("grant_type");
        if (grant_types == null || grant_types.length < 1) {
            return createErrorResponse("Grant type not provided");
        }

        // This is token request
        if ("authorization_code".equals(grant_types[0])) {
            return processTokenRequest(parameterMap);
        }

        objectBuilder.add("Error", "Invalid grant type");

        return objectBuilder.build();
    }

    private static JsonObject processTokenRequest(final Map<String, String[]> parameterMap) {
        // Verify auth code
        final String[] codes = parameterMap.get("code");
        if (codes == null || codes.length < 1) {
            return createErrorResponse("Authorization code parameter provided");
        }

        // Get token object by auth code
        final OpenIDConnectObject tokenObject;
        try {
            tokenObject = (OpenIDConnectObject) TokenStorage.getByAuthCode(codes[0]);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Invalid authorization code");
        }

        // If valid store by access token
        try {
            TokenStorage.addByAccessToken(codes[0], tokenObject.getAccessToken(), tokenObject);
        } catch (FrameworkCheckedException e) {
            return createErrorResponse(e, "Failure to store token");
        }


        final JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("access_token", tokenObject.getAccessToken());
        objectBuilder.add("token_type", "Bearer");
        objectBuilder.add("id_token", tokenObject.getIdToken());

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
