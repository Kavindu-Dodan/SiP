package Models;

public class SharedIdentityObject extends OpenIDConnectObject {

    private final String sharedIdentityToken;

    public SharedIdentityObject(final String accessToken,
                                final String authCode,
                                final String idToken,
                                final String sharedIdentityToken) {
        super(accessToken, authCode, idToken);
        this.sharedIdentityToken = sharedIdentityToken;
    }

    public String getSharedIdentityToken() {
        return this.sharedIdentityToken;
    }
}
