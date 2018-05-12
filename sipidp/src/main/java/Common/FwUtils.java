package Common;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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

    public static long getCurrentTimeStamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }


}
