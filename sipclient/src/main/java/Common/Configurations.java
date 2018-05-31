package Common;

public class Configurations {

    private static String clientIdForIdToken = "";
    private static String clientSecretForIdToken = "";
    private static String authEndpointForIdToken = "";
    private static String tokenEndpointForIdToken = "";

    private static String clientIdForIdSip = "";
    private static String clientSecretForIdSip = "";
    private static String authEndpointForIdSip = "";
    private static String tokenEndpointForIdSip = "";

    public static String getClientIdForIdToken() {
        return clientIdForIdToken;
    }

    public static void setClientIdForIdToken(String clientIdForIdToken) {
        Configurations.clientIdForIdToken = clientIdForIdToken;
    }

    public static String getClientSecretForIdToken() {
        return clientSecretForIdToken;
    }

    public static void setClientSecretForIdToken(String clientSecretForIdToken) {
        Configurations.clientSecretForIdToken = clientSecretForIdToken;
    }

    public static String getAuthEndpointForIdToken() {
        return authEndpointForIdToken;
    }

    public static void setAuthEndpointForIdToken(String authEndpointForIdToken) {
        Configurations.authEndpointForIdToken = authEndpointForIdToken;
    }

    public static String getTokenEndpointForIdToken() {
        return tokenEndpointForIdToken;
    }

    public static void setTokenEndpointForIdToken(String tokenEndpointForIdToken) {
        Configurations.tokenEndpointForIdToken = tokenEndpointForIdToken;
    }

    public static String getClientIdForIdSip() {
        return clientIdForIdSip;
    }

    public static void setClientIdForIdSip(String clientIdForIdSip) {
        Configurations.clientIdForIdSip = clientIdForIdSip;
    }

    public static String getClientSecretForIdSip() {
        return clientSecretForIdSip;
    }

    public static void setClientSecretForIdSip(String clientSecretForIdSip) {
        Configurations.clientSecretForIdSip = clientSecretForIdSip;
    }

    public static String getAuthEndpointForIdSip() {
        return authEndpointForIdSip;
    }

    public static void setAuthEndpointForIdSip(String authEndpointForIdSip) {
        Configurations.authEndpointForIdSip = authEndpointForIdSip;
    }

    public static String getTokenEndpointForIdSip() {
        return tokenEndpointForIdSip;
    }

    public static void setTokenEndpointForIdSip(String tokenEndpointForIdSip) {
        Configurations.tokenEndpointForIdSip = tokenEndpointForIdSip;
    }
}
