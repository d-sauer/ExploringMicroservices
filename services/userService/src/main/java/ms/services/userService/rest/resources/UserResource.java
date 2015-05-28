package ms.services.userService.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.userService.core.model.entities.User;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by davor on 22/05/15.
 */
public class UserResource extends ResourceSupport implements Logger {

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User toUser() {
        User user = new User();

        try {
            ObjectUtils.mapValues(this, user);
        } catch (Exception e) {
            error("Can't map values", e);
        }

        return user;
    }
}
