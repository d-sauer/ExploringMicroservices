package ms.api.service.discover;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackageClasses = { ms.api.service.discover.rest.DiscoverController.class })
public class DiscoverConfiguration {

}
