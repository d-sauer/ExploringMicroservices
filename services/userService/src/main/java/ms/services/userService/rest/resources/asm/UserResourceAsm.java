package ms.services.userService.rest.resources.asm;

import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.userService.core.model.entities.User;
import ms.services.userService.rest.mvc.UserController;
import ms.services.userService.rest.resources.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by davor on 22/05/15.
 */
public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource> implements Logger{

    public UserResourceAsm() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = new UserResource();

        try {
            ObjectUtils.mapValues(user, resource);
        } catch (Exception e) {
            error("Can't map value", e);
        }

        // Add HATEOAS links
        // ----

        return resource;
    }
}
