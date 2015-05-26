package ms.services.userService.core.services.exceptions;

import ms.commons.logging.LoggedException;

/**
 * Created by davor on 22/05/15.
 */
public class UserCreationException extends LoggedException {

    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Throwable cause) {
        super(message);
        error(message, cause);
    }

}

