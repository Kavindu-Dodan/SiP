package Common;

public class Constants {

    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private static final String CONSENT_FIELD = "consent_grant";


    private static final String INVALID_CREDENTIALS_FIELD = "INVALID_AUTHENTICATION";


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
}
