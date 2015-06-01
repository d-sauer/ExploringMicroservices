package ms.services.bankService;

import ms.commons.logging.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfigurationAuditDB.class, AppConfigurationBankDB.class })
public class AppConfiguration implements Logger {



}
