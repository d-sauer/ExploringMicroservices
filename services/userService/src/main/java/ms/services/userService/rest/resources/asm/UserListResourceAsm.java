package ms.services.userService.rest.resources.asm;

import ms.services.userService.core.model.entities.User;
import ms.services.userService.rest.mvc.UserController;
import ms.services.userService.rest.resources.UserListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by davor on 22/05/15.
 */
public class UserListResourceAsm extends ResourceAssemblerSupport<List<User>, UserListResource> {

    public UserListResourceAsm() {
        super(UserController.class, UserListResource.class);
    }

    @Override
    public UserListResource toResource(List<User> users) {
        UserListResource resource = new UserListResource();
        resource.setUsers(new UserResourceAsm().toResources(users));

        return resource;
    }
}
