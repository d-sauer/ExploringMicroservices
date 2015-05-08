package ms.api.service.autoconfigure;

import ms.api.service.discover.rest.DiscoverController;
import ms.commons.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by davor on 07/05/15.
 */
@Configuration
public class CxpAutoConfiguration implements Logger {

    @PostConstruct
    private void init() {
        info("Enable CXP");
    }

    @Bean
    public DiscoverController getDiscoverController() {
        trace("CXP Discovery controller");
        return new DiscoverController();
    }

}
