package storage;

import Models.SipUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SipUserStorage {

    private static final Map<String, SipUser> USER_MAP = new HashMap<>();

    private SipUserStorage() {
    }

    public static void addNewUser(final SipUser user) {
        USER_MAP.put(user.getUsername(), user);
    }

    public static Collection<SipUser> getSipUsers() {
        return USER_MAP.values();
    }
}
