package ms.services.bankService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.commons.logging.Logger;
import ms.commons.util.PackageUtils;
import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.audit.repositories.AuditRepository;
import ms.services.bankService.core.audit.services.impl.AuditServiceImpl;
import ms.services.bankService.rest.mvc.AuditController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
        entityManagerFactoryRef = "auditEntityManagerFactory",
        transactionManagerRef = "auditTransactionManager",
        basePackageClasses = { AuditRepository.class })
@Import({DataSourceAuditProperties.class})
@ComponentScan(basePackageClasses = {AuditController.class, AuditServiceImpl.class})
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

}
