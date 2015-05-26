package ms.services.userService.core.services;

import ms.services.userService.core.model.entities.Address;

import java.util.List;

/**
 * Created by davor on 21/05/15.
 */
public interface AddressService {

    Address createAddress(String userName, Address address);

    Address updateAddress(Long addressId, Address address);

    boolean removeAddress(Long addressId);

    List<Address> findUserAddresses(String userName);


}
