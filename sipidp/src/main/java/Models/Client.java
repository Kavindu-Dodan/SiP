package Models;

public class Client {

    private final String clientId;
    private final String credentials;
    private final String redirectUrl;

    public Client(final String clientId, final String credentials, final String redirectUrl) {
        this.clientId = clientId;
        this.credentials = credentials;
        this.redirectUrl = redirectUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
