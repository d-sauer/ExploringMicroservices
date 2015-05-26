package ms.services.userService.core.repositories;

import ms.services.userService.core.model.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by davor on 22/05/15.
 */
public interface UserRepository extends CrudRepository<User, String> {


}
