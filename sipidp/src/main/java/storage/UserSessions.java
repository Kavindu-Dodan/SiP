package storage;

import Common.Exceptions.FrameworkCheckedException;

import java.util.HashMap;
import java.util.Map;

public class UserSessions {

    // SessionID vs End User ID
    private static final Map<String, String> SESSION_MAP = new HashMap<>();

    private UserSessions() {
    }

    public static void addUserSession(final String sessionId, final String endUserId) {
        SESSION_MAP.put(sessionId, endUserId);
    }

    public static boolean checkLoggedIn(final String sessionId) {
        return SESSION_MAP.containsKey(sessionId);
    }

    public static String getLoggedInUser(final String sessionId) throws FrameworkCheckedException {
        if (checkLoggedIn(sessionId)) {
            return SESSION_MAP.get(sessionId);
        }

        throw new FrameworkCheckedException("User not found");
    }
}
