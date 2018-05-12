package Control.Token;

import Common.Exceptions.FrameworkCheckedException;
import Models.OpenIDConnectObject;
import Models.TokenObject;
import storage.TokenStorage;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TokenRequestProcessor {

    private TokenRequestProcessor() {
    }

    public static JsonObject processRequest(final HttpServletRequest request) throws FrameworkCheckedException {
        final Map<String, String[]> parameterMap = request.getParameterMap();

        // Grant type - grant_type
        final String[] grant_types = parameterMap.get("grant_type");
        if (grant_types.length < 1) {
            throw new FrameworkCheckedException("Grant type not defined");
        }

        // This is token request
        if ("authorization_code".equals(grant_types[0])) {
            return processTokenRequest(parameterMap);
        }

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("Error", "Invalid request");

        return objectBuilder.build();
    }

    private static JsonObject processTokenRequest(final Map<String, String[]> parameterMap) throws FrameworkCheckedException {
        // Verify auth code
        final String[] codes = parameterMap.get("code");
        if (codes.length < 1) {
            throw new FrameworkCheckedException("Missing authorization code");
        }

        // Get token object by auth code
        OpenIDConnectObject tokenObject = (OpenIDConnectObject) TokenStorage.getByAuthCode(codes[0]);

        // If valid store by access token
        TokenStorage.addByAccessToken(codes[0], tokenObject.getAccessToken(), tokenObject);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("access_token", tokenObject.getAccessToken());
        objectBuilder.add("token_type", "Bearer");
        objectBuilder.add("id_token", tokenObject.getIdToken());

        return objectBuilder.build();

    }


}
