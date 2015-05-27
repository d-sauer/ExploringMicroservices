package ms.api.service.autoconfigure;

import ms.api.service.discover.properties.ApiDiscoverProperties;
import ms.api.service.discover.rest.DiscoverController;
import ms.api.service.util.database.BaseJpaDataSourceProperties;
import ms.commons.logging.Logger;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Created by davor on 07/05/15.
 */
@Configuration
@Import({SwaggerConfig.class, BaseJpaDataSourceProperties.class})
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

    @Bean
    public ApiDiscoverProperties getApiDiscoverProperties() {
        return new ApiDiscoverProperties();
    }


}
