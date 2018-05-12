package storage;

import Common.Exceptions.FrameworkCheckedException;
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

    public static TokenObject getByAuthCode(final String authCode) throws FrameworkCheckedException {
        if (!TOKEN_BY_AUTH_CODE.containsKey(authCode)) {
            throw new FrameworkCheckedException("Invalid authorization code");
        }

        // Usage of auth code removes token. Later we must add it to access token based map
        TokenObject tokenObject = TOKEN_OBJECT_MAP.get(TOKEN_BY_AUTH_CODE.get(authCode));

        TOKEN_OBJECT_MAP.remove(TOKEN_BY_AUTH_CODE.get(authCode));
        TOKEN_BY_AUTH_CODE.remove(authCode);

        return tokenObject;
    }

    // For access token based storage
    public static void addByAccessToken(
            final String authCode,
            final String accessToken,
            final TokenObject tokenObject) throws FrameworkCheckedException {
        // verify not stored against auth code
        if (TOKEN_BY_AUTH_CODE.containsKey(authCode)) {
            throw new FrameworkCheckedException("Invalid token addition");
        }

        TOKEN_OBJECT_MAP.put(tokenObject.getObjId(), tokenObject);
        TOKEN_BY_ACCESS_CODE.put(accessToken, tokenObject.getObjId());
    }
}
