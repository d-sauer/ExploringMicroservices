package ms.services.bankService;

import ms.api.service.util.database.BaseDataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by davor on 02/06/15.
 */
@Configuration
@ConfigurationProperties(prefix = "datasource.bank")
public class DataSourceBankProperties extends BaseDataSourceProperties {
}
