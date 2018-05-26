package Common;

public class Constants {

    private static final String IDENTITY_SHARE_SCOPE = "identity_share";

    private static final String HOST = "localhost";

    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String CONSENT_FIELD = "consent_grant";
    private static final String ADD_USER_FILED = "Add User";
    private static final String ADD_PROVIDER_FIELD = "Add Provider";

    private static final String INVALID_CREDENTIALS_FIELD = "INVALID_AUTHENTICATION";

    private static final String CONTEXT_ROOT = "/sip-identity-provider";

    private static final long TOKEN_VALIDITY = 60 * 60;


    public static String getHOST() {
        return HOST;
    }

    public static String getIdentityShareScope() {
        return IDENTITY_SHARE_SCOPE;
    }

    public static String getUsernameField() {
        return USERNAME_FIELD;
    }

    public static String getPasswordField() {
        return PASSWORD_FIELD;
    }

    public static String getInvalidCredentialsField() {
        return INVALID_CREDENTIALS_FIELD;
    }

    public static String getConsentField() {
        return CONSENT_FIELD;
    }

    public static String getAddUserFiled() {
        return ADD_USER_FILED;
    }

    public static String getContextRoot() {
        return CONTEXT_ROOT;
    }

    public static String getAddProviderField() {
        return ADD_PROVIDER_FIELD;
    }

    public static long getTokenValidity() {
        return TOKEN_VALIDITY;
    }
}
