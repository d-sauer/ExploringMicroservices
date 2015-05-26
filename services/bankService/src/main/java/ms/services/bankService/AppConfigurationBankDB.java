package ms.services.bankService;

import ms.api.service.MicroserviceNamespace;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by davor on 26/05/15.
 */
@Configuration
@EntityScan(basePackageClasses = { ms.services.bankService.core.bank.model.entities.Account.class })
@EnableJpaRepositories(basePackageClasses = { ms.services.bankService.core.bank.repositories.AccountRepository.class },
                       entityManagerFactoryRef = "bankEntityManager",
                       transactionManagerRef = "bankTransactionManagement")
public class AppConfigurationBankDB {

    @Bean(name = "bankDataSource")
    @MicroserviceNamespace
    @ConfigurationProperties(prefix="datasource.bank")
    public DataSource bankDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "bankEntityManager")
    @DependsOn("bankTransactionManagement")
    public LocalContainerEntityManagerFactoryBean bankEntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(bankDataSource())
                .packages(ms.services.bankService.core.bank.model.entities.Account.class)
                .persistenceUnit("bankPersistanceUnit")
                .build();
    }

    @Bean(name = "bankTransactionManagement")
    public PlatformTransactionManager bankTransactionManagement() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(bankDataSource());
        transactionManager.setPersistenceUnitName("bankPersistanceUnit");
        return transactionManager;
    }
}
