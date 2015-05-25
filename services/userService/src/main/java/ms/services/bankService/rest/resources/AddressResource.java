package ms.services.userService.rest.resources;

import ms.services.userService.core.model.entities.Address;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by davor on 22/05/15.
 */
public class AddressResource extends ResourceSupport {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address toAddress() {
        Address address = new Address();
        address.setAddress(this.address);

        return address;
    }
}
