package ms.services.bankService;

import ms.api.service.MicroserviceNamespace;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by davor on 26/05/15.
 */
@Configuration
@EntityScan(basePackageClasses = {ms.services.bankService.core.audit.model.entities.Audit.class})
@EnableJpaRepositories(basePackageClasses = { ms.services.bankService.core.audit.repositories.AuditRepository.class },
                       entityManagerFactoryRef = "auditEntityManager",
                       transactionManagerRef = "auditTransactionManagement")
public class AppConfigurationAuditDB {

    @Bean(name = "auditDataSource")
    @MicroserviceNamespace
    @ConfigurationProperties(prefix = "datasource.audit")
    public DataSource auditDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "auditEntityManager")
    @DependsOn("auditTransactionManagement")
    public LocalContainerEntityManagerFactoryBean auditEntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(auditDataSource())
                .packages(ms.services.bankService.core.audit.model.entities.Audit.class)
                .persistenceUnit("auditPersistanceUnit")
                .build();
    }

    @Bean(name = "auditTransactionManagement")
    public PlatformTransactionManager auditTransactionManagement() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(auditDataSource());
        transactionManager.setPersistenceUnitName("auditPersistanceUnit");
        return transactionManager;
    }
}
