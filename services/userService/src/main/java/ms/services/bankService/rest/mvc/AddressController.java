package ms.services.userService.rest.mvc;

import ms.commons.logging.Logger;
import ms.services.userService.core.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by davor on 22/05/15.
 */
@RequestMapping("/user/address")
public class AddressController implements Logger {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity findUserAdresses(@RequestParam String userName) {
        debug("Get addresses for: {}", userName);

        // TODO implementation..
        return null;
    }

}
