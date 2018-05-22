package storage;

import Common.Exceptions.FrameworkCheckedException;
import Models.Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Clients {

    private static final Map<String, Client> CLIENT_MAP = new HashMap<>();

    static {
        Client def = new Client("MyClient", "test", "test", "http://test");
        addClient(def);
    }

    private Clients() {
    }

    public static void addClient(final Client newClient) {
        CLIENT_MAP.put(newClient.getClientId(), newClient);
    }

    public static Client getClientOnId(final String clientId) throws FrameworkCheckedException {
        if (CLIENT_MAP.containsKey(clientId)) {
            return CLIENT_MAP.get(clientId);
        }

        throw new FrameworkCheckedException("Client not found");
    }

    public static boolean authenticate(final String username, final String password) {
        return CLIENT_MAP.containsKey(username) && CLIENT_MAP.get(username).authenticate(password);
    }

    public static Collection<Client> getClients() {
        return CLIENT_MAP.values();
    }

}
