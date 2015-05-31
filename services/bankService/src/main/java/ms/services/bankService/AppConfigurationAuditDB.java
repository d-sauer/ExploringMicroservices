package ms.services.bankService;

import ms.api.service.util.database.BaseDataSourceProperties;
import ms.api.service.util.database.DatabaseUtils;
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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "auditEntityManagerFactory",
        transactionManagerRef = "auditTransactionManager",
        basePackageClasses = { AuditRepository.class })
@EnableTransactionManagement
@EnableConfigurationProperties(AppConfigurationAuditDB.DataSourceAuditProperties.class)
public class AppConfigurationAuditDB implements Logger {

    @Autowired
    private DataSourceAuditProperties auditProperties;

    @Bean(name = "auditDataSource")
    public DataSource auditDataSource() {
        return DatabaseUtils.createDataSource(auditProperties, this);
    }

    @Bean(name = "auditEntityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean(name = "auditEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(auditDataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan(PackageUtils.getPackageNames(Audit.class));
        lef.setPersistenceUnitName("auditPersistanceUnit");
        lef.setJpaProperties(auditProperties.getJpaProperties());
        lef.afterPropertiesSet();
        return lef.getObject();
    }

    @Bean(name = "auditTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @ConfigurationProperties(prefix = "datasource.audit")
    static class DataSourceAuditProperties extends BaseDataSourceProperties { }
}
