package ms.services.time;

import ms.api.service.EnableCxp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { ms.services.time.properties.ServiceProperties.class,
                                      ms.services.time.rest.TimeController.class })
// test that CXP will be enabled only once, regarding multiple annotations in same project
@EnableCxp
public class AppConfiguration {

}
