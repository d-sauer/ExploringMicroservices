package ms.services.userService.core.repositories;

import ms.services.userService.core.model.entities.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by davor on 22/05/15.
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

//    List<Address> findUserAddresses(String userName);

}
