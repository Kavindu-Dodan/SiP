package Models;

public class OAuthTokenObject extends TokenObject {

    private String accessToken;

    public OAuthTokenObject(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
