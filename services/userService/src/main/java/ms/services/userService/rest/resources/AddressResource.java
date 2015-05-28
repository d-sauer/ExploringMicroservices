package ms.services.userService.rest.resources;

import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.userService.core.model.entities.Address;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by davor on 22/05/15.
 */
public class AddressResource extends ResourceSupport implements Logger {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address toAddress() {
        Address address = new Address();

        try {
            ObjectUtils.mapValues(this, address);
        } catch (Exception e) {
            error("Can't map value", e);
        }

        return address;
    }
}
