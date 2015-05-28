package ms.services.bankService;

import ms.api.service.util.database.BaseDataSourceProperties;
import ms.api.service.util.database.DatabaseUtils;
import ms.commons.logging.Logger;
import ms.commons.pack.PackageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "bankEntityManager",
        transactionManagerRef = "bankTransactionManager",
        basePackageClasses = { ms.services.bankService.core.bank.repositories.AccountRepository.class })
@EnableConfigurationProperties( {AppConfigurationBankDB.DataSourceBankProperties.class})
public class AppConfigurationBankDB implements Logger {

    @Autowired
    private DataSourceBankProperties bankProperties;

    @Bean(name = "bankEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        trace("Bank JPA properties: {}", bankProperties.getJpaProperties());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DatabaseUtils.createDataSource(bankProperties, this));
        em.setPackagesToScan(PackageUtils.getPackageNames(ms.services.bankService.core.bank.model.entities.Account.class));
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(bankProperties.getJpaProperties());
        em.setPersistenceUnitName("bankPersistanceUnit");

        return em;
    }

    @Bean(name = "bankTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory bankEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bankEntityManager);

        return transactionManager;
    }

    @ConfigurationProperties(prefix = "datasource.bank")
    static class DataSourceBankProperties extends BaseDataSourceProperties { }

}
