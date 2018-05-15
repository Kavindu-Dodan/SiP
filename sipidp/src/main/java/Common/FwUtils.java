package Common;

import Common.Exceptions.FrameworkCheckedException;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

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
}
