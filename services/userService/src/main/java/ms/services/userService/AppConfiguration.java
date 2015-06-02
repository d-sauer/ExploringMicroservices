package ms.services.userService;

import ms.commons.logging.Logger;
import ms.services.userService.core.services.impl.UserServiceImpl;
import ms.services.userService.rest.mvc.UserController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfigurationUserDB.class})
public class AppConfiguration implements Logger {


}
