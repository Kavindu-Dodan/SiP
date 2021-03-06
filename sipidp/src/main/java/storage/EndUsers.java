package storage;

import Common.Exceptions.FrameworkCheckedException;
import Models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EndUsers {

    private static final Map<String, User> USER_MAP = new HashMap<>();

    private EndUsers() {
    }

    public static void addNewUser(final User user) {
        USER_MAP.put(user.getUsername(), user);
    }

    public static User getUserByUsername(final String username) throws FrameworkCheckedException {
        if (USER_MAP.containsKey(username)) {
            return USER_MAP.get(username);
        }

        throw new FrameworkCheckedException("User not found");
    }

    public static boolean authenticate(final String username, final String password) {
        return USER_MAP.containsKey(username) && USER_MAP.get(username).authenticate(password);
    }

    public static Collection<User> getUsers() {
        return USER_MAP.values();
    }
}
