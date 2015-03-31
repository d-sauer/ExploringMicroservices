package ms.services.time;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { ms.services.time.rest.TimeController.class })
public class AppConfiguration {

}
