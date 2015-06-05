package ms.services.bankService;

import ms.api.service.autoconfig.jdbc.BaseJdbcConfiguration;
import ms.api.service.autoconfig.jdbc.BaseJdbcFactoryBean;
import ms.commons.logging.Logger;
import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.repositories.AccountRepository;
import ms.services.bankService.core.bank.services.impl.AccountServiceImpl;
import ms.services.bankService.rest.mvc.AccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = AppConfigurationBankDB.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = AppConfigurationBankDB.TRANSACTION_MANAGER_REF,
        basePackageClasses = {AccountRepository.class, Account.class})
@ComponentScan(basePackageClasses = {AccountController.class, AccountServiceImpl.class})
@BaseJdbcConfiguration(prefix = "datasource.bank", persistanceUnitName = "bankPersistanceUnit")
public class AppConfigurationBankDB implements Logger {

    public static final String ENTITY_MANAGER_FACTORY_REF = "bankEntityManagerFactory";
    public static final String TRANSACTION_MANAGER_REF = "bankTransactionManager";

    @Autowired
    private BaseJdbcFactoryBean jdbcFactoryBean;

    @PostConstruct
    private void postConstruct() {
        trace("Bank AppConfigurationBankDB");
    }

    @Bean(name = "bankDataSource")
    public DataSource bankDataSource() {
        return jdbcFactoryBean.getDataSource(AppConfigurationBankDB.class);
    }

    @Bean(name = AppConfigurationBankDB.ENTITY_MANAGER_FACTORY_REF)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return jdbcFactoryBean.getEntityManagerFactoryBean(AppConfigurationBankDB.class);
    }

    @Bean(name = AppConfigurationBankDB.TRANSACTION_MANAGER_REF)
    public PlatformTransactionManager transactionManager() {
        return jdbcFactoryBean.getJpaTransactionManager(AppConfigurationBankDB.class);
    }

}
