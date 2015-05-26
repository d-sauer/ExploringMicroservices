package ms.services.bankService;

import ms.commons.logging.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { ms.services.bankService.AppConfigurationBankDB.class })
public class AppConfiguration implements Logger {




}
