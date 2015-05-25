package ms.services.userService.core.services.exceptions;

import ms.commons.logging.LoggedException;

/**
 * Created by davor on 22/05/15.
 */
public class UserNotFoundException extends LoggedException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
