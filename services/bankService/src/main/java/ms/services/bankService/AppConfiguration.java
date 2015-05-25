package ms.services.bankService;

import ms.api.service.MicroserviceNamespace;
import ms.commons.logging.Logger;
import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.bank.model.entities.Account;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {  })
@EntityScan(basePackageClasses = { Account.class })
public class AppConfiguration implements Logger {

    @Bean
    @Primary
    @MicroserviceNamespace
    @ConfigurationProperties(prefix="datasource.bank")
    public DataSource bankDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bankEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(bankDataSource())
                .packages(Account.class)
                .persistenceUnit("bankEMF")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager bankTransactionManagement() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(bankDataSource());
        transactionManager.setPersistenceUnitName("bankEMF");
        return transactionManager;
    }


    @Bean
    @MicroserviceNamespace
    @ConfigurationProperties(prefix="datasource.audit")
    public DataSource auditDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean auditEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(auditDataSource())
                .packages(Audit.class)
                .persistenceUnit("auditEMF")
                .build();
    }

    @Bean
    public PlatformTransactionManager auditTransactionManagement() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(auditDataSource());
        transactionManager.setPersistenceUnitName("auditEMF");
        return transactionManager;
    }

}
