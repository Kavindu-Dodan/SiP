package storage;

import Models.TokenObject;

import java.util.HashMap;
import java.util.Map;

public class TokenStorage {

    private static final Map<String, String> TOKEN_BY_AUTH_CODE = new HashMap<>();
    private static final Map<String, String> TOKEN_BY_ACCESS_CODE = new HashMap<>();

    private static final Map<String, TokenObject> TOKEN_OBJECT_MAP = new HashMap<>();

    private TokenStorage() {
    }

    // Temporal store - store for auth code
    public static void addByAuthCode(final String authCode, final TokenObject tokenObject) {
        TOKEN_OBJECT_MAP.put(tokenObject.getObjId(), tokenObject);
        TOKEN_BY_AUTH_CODE.put(authCode, tokenObject.getObjId());
    }

    // For access token based storage
    public static void addByAccessToken(final String accessToken, final TokenObject tokenObject) {
        TOKEN_OBJECT_MAP.put(tokenObject.getObjId(), tokenObject);
        TOKEN_BY_ACCESS_CODE.put(accessToken, tokenObject.getObjId());
    }
}
