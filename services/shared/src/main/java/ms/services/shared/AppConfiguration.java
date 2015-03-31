package ms.services.shared;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = { ms.services.shared.rest.DiscoverController.class })
public class AppConfiguration {

}
