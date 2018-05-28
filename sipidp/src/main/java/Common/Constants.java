package Common;

import static java.lang.String.format;

public class Constants {

    private static final String IDENTITY_SHARE_SCOPE = "identity_share";

    private static final String HOST = System.getProperty("host");
    private static final String PORT = System.getProperty("port");
    private static final String CONTEXT_ROOT = "/sip-identity-provider";

    private static final String THIS_ISSUER =
            format("http://%s:%s%s", getHOST(), getPORT(), getContextRoot());

    private static final String DISCOVERY_ENDPOINT = "/.well-known/openid-configuration";

    private static final String AUTHORIZARION_ENDPOINT = "/authorization";
    private static final String TOKEN_ENDPOINT = "/Token";

    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String CONSENT_FIELD = "consent_grant";
    private static final String ADD_USER_FILED = "Add User";
    private static final String ADD_PROVIDER_FIELD = "Add Provider";

    private static final String INVALID_CREDENTIALS_FIELD = "INVALID_AUTHENTICATION";

    private static final long TOKEN_VALIDITY = 60 * 60;


    public static String getHOST() {
        return HOST;
    }

    public static String getPORT() {
        return PORT;
    }

    public static String getThisIssuer() {
        return THIS_ISSUER;
    }

    public static String getAuthorizarionEndpoint() {
        return AUTHORIZARION_ENDPOINT;
    }

    public static String getTokenEndpoint() {
        return TOKEN_ENDPOINT;
    }

    public static String getDiscoveryEndpoint() {
        return DISCOVERY_ENDPOINT;
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
