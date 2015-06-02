package ms.services.userService;

import ms.api.service.autoconfig.database.BaseDataSourceFactory;
import ms.commons.logging.Logger;
import ms.services.userService.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManagerFactory",
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
        return dataSourceFactory.get(userProperties).getDataSource();
    }

    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        return dataSourceFactory.get(userProperties).getEntityManagerFactoryBean("userPersistanceUnit", new String[] {"ms.services.userService.core.model.entities"});
    }

    @Bean(name = "userTransactionManager")
    public JpaTransactionManager transactionManager(){
        return dataSourceFactory.get(userProperties).getJpaTransactionManager("userPersistanceUnit", new String[]{"ms.services.userService.core.model.entities"});
    }

}
