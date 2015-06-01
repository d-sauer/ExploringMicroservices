package ms.services.userService.core.services.impl;

import ms.services.userService.core.model.entities.User;
import ms.services.userService.core.repositories.UserRepository;
import ms.services.userService.core.services.UserService;
import ms.services.userService.core.services.exceptions.UserCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by davor on 22/05/15.
 */
@Service
@Transactional("userTransactionManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String userName, User user) {
        throw new UserCreationException("Not implemented");
//        return userRepository.findOne(userName)
    }

    @Override
    public void removeUser(String userName) {
        userRepository.delete(userName);
    }
}
