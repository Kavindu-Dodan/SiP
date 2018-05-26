package Models;

public class SharedIdentityObject extends OpenIDConnectObject {

    private final String sharedIdentityToken;

    public SharedIdentityObject(final String authCode,
                                final String accessToken,
                                final String idToken,
                                final String sharedIdentityToken) {
        super(authCode, accessToken, idToken);
        this.sharedIdentityToken = sharedIdentityToken;
    }

    public String getSharedIdentityToken() {
        return this.sharedIdentityToken;
    }
}
