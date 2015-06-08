package ms.services.bankService;

import ms.api.service.autoconfig.jdbc.EnableCustomJdbcConfiguration;
import ms.api.service.autoconfig.jdbc.CustomJdbcFactoryBean;
import ms.commons.logging.Logger;
import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.audit.repositories.AuditRepository;
import ms.services.bankService.core.audit.services.impl.AuditServiceImpl;
import ms.services.bankService.rest.mvc.AuditController;
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
        entityManagerFactoryRef = AppConfigurationAuditDB.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = AppConfigurationAuditDB.TRANSACTION_MANAGER_REF,
        basePackageClasses = { AuditRepository.class, Audit.class })
@ComponentScan(basePackageClasses = {AuditController.class, AuditServiceImpl.class})
@EnableCustomJdbcConfiguration(prefix = "datasource.audit", persistanceUnitName = "auditPersistanceUnit")
public class AppConfigurationAuditDB implements Logger {

    public static final String ENTITY_MANAGER_FACTORY_REF = "auditEntityManagerFactory";
    public static final String TRANSACTION_MANAGER_REF = "auditTransactionManager";

    @Autowired
    private CustomJdbcFactoryBean jdbcFactoryBean;

    @PostConstruct
    private void postConstruct() {
        trace("Audit AppConfigurationAuditDB");
    }

    @Bean(name = "auditDataSource")
    public DataSource auditDataSource() {
        return jdbcFactoryBean.getDataSource(AppConfigurationAuditDB.class);
    }

    @Bean(name =  AppConfigurationAuditDB.ENTITY_MANAGER_FACTORY_REF)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        return jdbcFactoryBean.getEntityManagerFactoryBean(AppConfigurationAuditDB.class);
    }

    @Bean(name = AppConfigurationAuditDB.TRANSACTION_MANAGER_REF)
    public PlatformTransactionManager transactionManager() {
        return jdbcFactoryBean.getJpaTransactionManager(AppConfigurationAuditDB.class);
    }

}
