package ms.services.userService.rest.mvc;

import ms.services.userService.core.model.entities.User;
import ms.services.userService.core.services.UserService;
import ms.services.userService.rest.resources.UserListResource;
import ms.services.userService.rest.resources.asm.UserListResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by davor on 21/05/15.
 */
@Controller
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<UserListResource> findAllUsers() {
        List<User> users = userService.findAllUsers();

        UserListResource userList = new UserListResourceAsm().toResource(users);

        return new ResponseEntity<UserListResource>(userList, HttpStatus.OK);
    }


}
