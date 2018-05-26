package Models;

public class Client {

    private final String clientName;
    private final String clientId;
    private final String clientPassword;
    private final String redirectUrl;

    public Client(final String clientName, final String clientId, final String credentials, final String redirectUrl) {
        this.clientName = clientName;
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

    public String getClientName() {
        return clientName;
    }

    public String getClientPassword() {
        return clientPassword;
    }
}
