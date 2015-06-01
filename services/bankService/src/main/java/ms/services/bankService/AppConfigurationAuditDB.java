package ms.services.bankService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.api.service.util.database.BaseDataSourceProperties;
import ms.commons.logging.Logger;
import ms.commons.util.PackageUtils;
import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.audit.repositories.AuditRepository;
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
        entityManagerFactoryRef = "auditEntityManagerFactory",
        transactionManagerRef = "auditTransactionManager",
        basePackageClasses = { AuditRepository.class })
@EnableTransactionManagement
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
        return dataSourceFactory.getDatasoDataSource(auditProperties);
    }

    @Bean(name = "auditEntityManager")
    public EntityManager entityManager(@Qualifier("auditEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean(name = "auditEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSourceFactory.getDatasoDataSource(auditProperties));
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan(PackageUtils.getPackageNames(Audit.class));
        lef.setPersistenceUnitName("auditPersistanceUnit");
        lef.setJpaProperties(auditProperties.getJpaProperties());
        lef.afterPropertiesSet();
        return lef.getObject();
    }

    @Bean(name = "auditTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("auditEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @ConfigurationProperties(prefix = "datasource.audit")
    public static class DataSourceAuditProperties extends BaseDataSourceProperties { }
}
