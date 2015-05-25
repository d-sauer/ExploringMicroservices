package ms.commons.logging;

/**
 * Created by davor on 22/05/15.
 */
public class LoggedException extends RuntimeException implements Logger {

    public LoggedException(String message) {
        super(message);
        error(message);
    }

    public LoggedException(String message, Throwable cause) {
        super(message, cause);
        error(message, cause);
    }

}
