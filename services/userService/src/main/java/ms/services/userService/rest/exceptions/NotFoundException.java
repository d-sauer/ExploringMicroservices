package ms.services.userService.rest.exceptions;

import ms.commons.logging.LoggedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by davor on 22/05/15.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends LoggedException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
