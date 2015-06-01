package ms.services.userService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.commons.logging.Logger;
import ms.services.userService.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager",
        basePackageClasses = {UserRepository.class})
public class AppConfigurationUserDB implements Logger {

    @Autowired
    private DataSourceUserProperties userProperties;

    @Autowired
    private BaseDataSourceFactory dataSourceFactory;

    @PostConstruct
    private void postConstruct() {
        trace("User AppConfigurationUserDB");
    }

    @Bean(name = "userDataSource")
    public DataSource dataSource(){
        return dataSourceFactory.getDatasoDataSource(userProperties);
    }

    @Bean(name = "userEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceFactory.getDatasoDataSource(userProperties));
        em.setPackagesToScan(new String[] {"ms.services.userService.core.model.entities"});
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(userProperties.getJpaProperties());
        em.setPersistenceUnitName("users");

        return em;
    }

    @Bean(name = "userTransactionManager")
    @DependsOn("userEntityManager")
    public JpaTransactionManager transactionManager(@Qualifier("userEntityManager") EntityManagerFactory entityManagerFactory){
        info("entityManagerFactory: {}", entityManagerFactory);

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

}
