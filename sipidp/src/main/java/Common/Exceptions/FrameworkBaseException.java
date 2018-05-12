package Common.Exceptions;

public class FrameworkBaseException extends RuntimeException {

    private static final String EXCEPTION_TYPE = "BASE_EXCEPTION";

    private final String customMessage;

    public FrameworkBaseException(final String message, final Throwable exp) {
        super(EXCEPTION_TYPE, exp);
        this.customMessage = message;
    }

    public FrameworkBaseException(final String message) {
        super(EXCEPTION_TYPE);
        this.customMessage = message;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
