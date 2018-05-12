package Models;

public class OpenIDConnectObject extends TokenObject {

    private final String authCode;
    private final String idToken;

    private String accessToken;

    public OpenIDConnectObject(
            final String authCode,
            final String accessToken,
            final String idToken) {
        this.authCode = authCode;
        this.accessToken = accessToken;
        this.idToken = idToken;
    }

    public String getIdToken() {
        return this.idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthCode() {
        return authCode;
    }
}
