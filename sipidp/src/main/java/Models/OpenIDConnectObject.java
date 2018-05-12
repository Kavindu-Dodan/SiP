package Models;

public class OpenIDConnectObject extends TokenObject {

    private final String idToken;

    public OpenIDConnectObject(final String accessToken, final String authCode, final String idToken) {
        super(accessToken, authCode);
        this.idToken = idToken;
    }

    public String getIdToken() {
        return this.idToken;
    }
}
