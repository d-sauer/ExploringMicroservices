package ms.services.bankService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.api.service.util.database.BaseDataSourceProperties;
import ms.commons.logging.Logger;
import ms.commons.util.PackageUtils;
import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.audit.repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "auditEntityManagerFactory",
        transactionManagerRef = "auditTransactionManager",
        basePackageClasses = { AuditRepository.class })
@EnableConfigurationProperties(AppConfigurationAuditDB.DataSourceAuditProperties.class)
public class AppConfigurationAuditDB implements Logger {

    @Autowired
    private DataSourceAuditProperties auditProperties;

    @Autowired
    private BaseDataSourceFactory dataSourceFactory;

    @PostConstruct
    private void postConstruct() {
        trace("Audit AppConfigurationAuditDB");
    }

    @Bean(name = "auditDataSource")
    public DataSource auditDataSource() {
        return dataSourceFactory.get(auditProperties).getDataSource();
    }

    @Bean(name = "auditEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        return dataSourceFactory.get(auditProperties).getEntityManagerFactoryBean("auditPersistanceUnit", PackageUtils.getPackageNames(Audit.class));
    }

    @Bean(name = "auditTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return dataSourceFactory.get(auditProperties).getJpaTransactionManager("auditPersistanceUnit", PackageUtils.getPackageNames(Audit.class));
    }

    @ConfigurationProperties(prefix = "datasource.audit")
    public static class DataSourceAuditProperties extends BaseDataSourceProperties { }
}
