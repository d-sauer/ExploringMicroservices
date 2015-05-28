package ms.api.service.autoconfig;

import ms.api.service.discover.properties.ApiDiscoverProperties;
import ms.api.service.discover.rest.DiscoverController;
import ms.api.service.autoconfig.database.BaseJpaDataSourceProperties;
import ms.api.service.util.database.BaseDataSourceProperties;
import ms.commons.logging.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Created by davor on 07/05/15.
 */
@Configuration
@Import({SwaggerConfig.class})
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

    @Bean(name = "baseJpaDataSourceProperties")
    @ConditionalOnBean(BaseDataSourceProperties.class)
    public BaseJpaDataSourceProperties baseJpaDataSourceProperties() {
        trace("BaseJpaDataSourceProperties from 'spring.jpa' property segment");
        return new BaseJpaDataSourceProperties();
    }

}
