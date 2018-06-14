package Models;

public class SipUser extends User {
    private final String issuer;

    public SipUser(final String username, final String issuer) {
        super(username);
        this.issuer = issuer;
    }

    public String getIssuer() {
        return issuer;
    }
}
