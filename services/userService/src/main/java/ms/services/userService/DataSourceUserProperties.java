package ms.services.userService;

import ms.api.service.util.database.BaseDataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by davor on 01/06/15.
 */
@Configuration
@ConfigurationProperties(prefix = "datasource.user")
public class DataSourceUserProperties extends BaseDataSourceProperties {
}
