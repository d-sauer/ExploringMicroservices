package ms.services.bankService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.api.service.util.database.BaseDataSourceProperties;
import ms.commons.logging.Logger;
import ms.commons.util.PackageUtils;
import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "bankEntityManagerFactory",
        transactionManagerRef = "bankTransactionManager",
        basePackageClasses = { AccountRepository.class })
@EnableConfigurationProperties( {AppConfigurationBankDB.DataSourceBankProperties.class})
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
        return dataSourceFactory.get(bankProperties).getEntityManagerFactoryBean("bankPersistanceUnit", PackageUtils.getPackageNames(Account.class));
    }

    @Bean(name = "bankTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return dataSourceFactory.get(bankProperties).getJpaTransactionManager("bankPersistanceUnit", PackageUtils.getPackageNames(Account.class));
    }

    @ConfigurationProperties(prefix = "datasource.bank")
    public static class DataSourceBankProperties extends BaseDataSourceProperties {
    }
}
