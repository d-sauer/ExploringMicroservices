package ms.services.userService.rest.resources.asm;

import ms.services.userService.core.model.entities.User;
import ms.services.userService.rest.mvc.UserController;
import ms.services.userService.rest.resources.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by davor on 22/05/15.
 */
public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource>{

    private Class<?> controllerClass;

    public UserResourceAsm() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = new UserResource();
        resource.setUserName(user.getUserName());
        resource.setPassword(user.getPassword());
        resource.setFirstName(user.getFirstName());
        resource.setLastName(user.getLastName());

        // Add HATEOAS links
        // ----

        return resource;
    }
}
