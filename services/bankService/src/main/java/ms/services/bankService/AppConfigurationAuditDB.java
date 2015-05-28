package ms.services.bankService;

import ms.api.service.util.database.DatabaseUtils;
import ms.commons.logging.Logger;
import ms.commons.util.PackageUtils;
import ms.api.service.util.database.BaseDataSourceProperties;
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

import javax.persistence.EntityManagerFactory;

/**
 * Created by davor on 26/05/15.
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "auditEntityManager",
        transactionManagerRef = "auditTransactionManager",
        basePackageClasses = { ms.services.bankService.core.audit.repositories.AuditRepository.class })
@EnableConfigurationProperties(AppConfigurationAuditDB.DataSourceAuditProperties.class)
public class AppConfigurationAuditDB implements Logger {

    @Autowired
    private DataSourceAuditProperties auditProperties;

    @Bean(name = "auditEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        trace("Audit JPA properties: {}", auditProperties.getJpaProperties());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DatabaseUtils.createDataSource(auditProperties, this));
        em.setPackagesToScan(PackageUtils.getPackageNames(ms.services.bankService.core.audit.model.entities.Audit.class));
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(auditProperties.getJpaProperties());
        em.setPersistenceUnitName("auditPersistanceUnit");

        return em;
    }

    @Bean(name = "auditTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory auditEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(auditEntityManager);

        return transactionManager;
    }

    @ConfigurationProperties(prefix = "datasource.audit")
    static class DataSourceAuditProperties extends BaseDataSourceProperties { }
}
