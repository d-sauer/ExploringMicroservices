package ms.services.userService.core.services.impl;

import ms.services.userService.core.model.entities.Address;
import ms.services.userService.core.model.entities.User;
import ms.services.userService.core.repositories.AddressRepository;
import ms.services.userService.core.repositories.UserRepository;
import ms.services.userService.core.services.AddressService;
import ms.services.userService.core.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by davor on 22/05/15.
 */
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address createAddress(String userName, Address address) {
        User user = userRepository.findOne(userName);
        if (user == null) {
            throw new UserNotFoundException("Username '" + userName + "' doesn't exist");
        }
        address.getUsers().add(user);

        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long addressId, Address address) {
        return null;
    }

    @Override
    public boolean removeAddress(Long addressId) {
        return false;
    }

    @Override
    public List<Address> findUserAddresses(String userName) {
        return null;
    }
}
