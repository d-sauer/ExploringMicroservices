package ms.services.bankService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.commons.logging.Logger;
import ms.commons.util.PackageUtils;
import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.repositories.AccountRepository;
import ms.services.bankService.core.bank.services.impl.AccountServiceImpl;
import ms.services.bankService.rest.mvc.AccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "bankEntityManagerFactory",
        transactionManagerRef = "bankTransactionManager",
        basePackageClasses = { AccountRepository.class })
@Import({DataSourceBankProperties.class})
@ComponentScan(basePackageClasses = {AccountController.class, AccountServiceImpl.class})
public class AppConfigurationBankDB implements Logger {

    @Autowired
    private DataSourceBankProperties bankProperties;

    @Autowired
    private BaseDataSourceFactory dataSourceFactory;

    @PostConstruct
    private void postConstruct() {
        trace("Bank AppConfigurationBankDB");
    }

    @Bean(name = "bankDataSource")
    public DataSource bankDataSource() {
        return dataSourceFactory.get(bankProperties).getDataSource();
    }

    @Bean(name = "bankEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return dataSourceFactory.get(bankProperties).getEntityManagerFactoryBean("bankEntityManagerFactory", "bankPersistanceUnit", PackageUtils.getPackageNames(Account.class));
    }

    @Bean(name = "bankTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return dataSourceFactory.get(bankProperties).getJpaTransactionManager("bankEntityManagerFactory", "bankPersistanceUnit", PackageUtils.getPackageNames(Account.class));
    }

}
