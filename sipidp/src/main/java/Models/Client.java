package Models;

public class Client {

    private final String clientId;
    private final String clientPassword;
    private final String redirectUrl;

    public Client(final String clientId, final String credentials, final String redirectUrl) {
        this.clientId = clientId;
        this.clientPassword = credentials;
        this.redirectUrl = redirectUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public boolean authenticate(final String password) {
        return this.clientPassword.equals(password);
    }
}
