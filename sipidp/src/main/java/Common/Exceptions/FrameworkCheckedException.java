package Common.Exceptions;

public class FrameworkCheckedException extends Exception {

    private static final String EXCEPTION_TYPE = "BASE_CHECKED_EXCEPTION";

    private final String customMessage;

    public FrameworkCheckedException(final String message, final Throwable exp) {
        super(EXCEPTION_TYPE, exp);
        this.customMessage = message;
    }

    public FrameworkCheckedException(final String message) {
        super(EXCEPTION_TYPE);
        this.customMessage = message;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
