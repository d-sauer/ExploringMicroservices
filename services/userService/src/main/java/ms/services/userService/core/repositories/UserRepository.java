package ms.services.userService.core.repositories;

import ms.services.userService.core.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by davor on 22/05/15.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {


}
