package Common;

import static java.lang.String.format;

public class Constants {

    private static final String HOST = System.getProperty("host");
    private static final String PORT = System.getProperty("port");
    private static final String CONTEXT_ROOT = "/sip-client";
    private static final String REDIRECT_ENDPOINT = "/redirect";

    private static final String MY_CONTEXT_PATH =
            format("http://%s:%s%s", getHOST(), getPORT(), getContextRoot());

    public static String getHOST() {
        return HOST;
    }

    public static String getPORT() {
        return PORT;
    }

    public static String getContextRoot() {
        return CONTEXT_ROOT;
    }

    public static String getMyContextPath() {
        return MY_CONTEXT_PATH;
    }

    public static String getRedirectEndpoint() {
        return REDIRECT_ENDPOINT;
    }
}
