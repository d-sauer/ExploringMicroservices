package ms.services.userService.core.services;

import ms.services.userService.core.model.entities.User;

import java.util.List;

/**
 * Created by davor on 21/05/15.
 */
public interface UserService {

    List<User> findAllUsers();

    User createUser(User user);

    User updateUser(String userName, User user);

    void removeUser(String userName);
}
