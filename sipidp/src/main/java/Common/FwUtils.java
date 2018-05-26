package Common;

import Common.Exceptions.FrameworkCheckedException;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;

import static java.lang.String.format;

public class FwUtils {

    private static final String CANDIDATE_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int CANDIDATE_STR_LENGTH = CANDIDATE_STRING.length();

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String getRandomId(final int length) {
        final StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(CANDIDATE_STRING.charAt(SECURE_RANDOM.nextInt(CANDIDATE_STR_LENGTH)));
        }

        return builder.toString();
    }

    public static String decodeBase64(final String base64Encoding) {
        return new String(Base64.getDecoder().decode(base64Encoding));
    }

    public static long getCurrentTimeStamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public static boolean isBasicAuth(final String headerValue) {
        return headerValue != null && headerValue.startsWith("Basic");
    }

    public static boolean isBearerAuth(final String headerValue) {
        return headerValue != null && headerValue.startsWith("Bearer");
    }

    public static String[] getCredentialsFromBasicHeader(final String headerValue) throws FrameworkCheckedException {
        if (!isBasicAuth(headerValue)) {
            throw new FrameworkCheckedException("Provided header value is not basic authentication");
        }

        final String[] splits = headerValue.split(" ");

        if (splits.length < 2) {
            throw new FrameworkCheckedException("Malformed basic header");
        }

        final String[] decodedCredentials = decodeBase64(splits[1]).split(":");

        if (decodedCredentials.length < 2) {
            throw new FrameworkCheckedException("Malformed basic header");
        }

        return decodedCredentials;
    }

    public static String getCredentialFromBearerHeader(final String headerValue) throws FrameworkCheckedException {
        if (!isBearerAuth(headerValue)) {
            throw new FrameworkCheckedException("Provided header value is not basic authentication");
        }

        final String[] splits = headerValue.split(" ");

        if (splits.length < 2) {
            throw new FrameworkCheckedException("Malformed basic header");
        }

        return splits[1];
    }

    public static void addHtmlHeaderWithTitle(
            final ServletOutputStream outputStream,
            final String title,
            final List<String> columnHeaders) throws IOException {

        outputStream.println(format("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>%s</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"/sip-identity-provider/jsp/css/bootstrap.min.css\">\n" +
                "\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>", title));

        outputStream.println("<div class=\"container\">\n");

        outputStream.println(format("<div class=\".container-fluid\">" +
                "        <br>" +
                "        <h2>%s</h2>" +
                "        <br>" +
                "    </div>", title));

        outputStream.println("<table class=\"table\">");

        outputStream.println("<thead  class=\"thead-light\">");
        outputStream.println("<tr>");
        columnHeaders.forEach(
                header -> {
                    try {
                        outputStream.println(format("<th>%s</th>", header));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        outputStream.println("</tr>");
        outputStream.println("</thead>");

    }

    public static void addHtmlFooter(final ServletOutputStream outputStream) throws IOException {
        outputStream.println("</table>");

        outputStream.println("<a class=\"btn btn-info\" href=\"/sip-identity-provider/admin\" role=\"button\">To Admin Page</a>");

        outputStream.println("</div>");
        outputStream.println("</body>");
        outputStream.println("</html>");
    }
}
