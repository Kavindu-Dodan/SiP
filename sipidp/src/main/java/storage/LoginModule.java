package storage;

import java.util.HashMap;
import java.util.Map;

public class LoginModule {

    private static final Map<String, String> userCredentialMap = new HashMap<>();

    static {
        userCredentialMap.put("admin", "admin");
        userCredentialMap.put("test", "test");
    }

    private LoginModule() {
    }

    public static boolean authenticate(final String username, final String password) {
        return userCredentialMap.containsKey(username)
                && userCredentialMap.get(username).equals(password);
    }
}
