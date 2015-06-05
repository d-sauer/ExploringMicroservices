package ms.services.userService;

import ms.api.service.autoconfig.jdbc.BaseJdbcConfiguration;
import ms.api.service.autoconfig.jdbc.BaseJdbcFactoryBean;
import ms.commons.logging.Logger;
import ms.services.userService.core.model.entities.User;
import ms.services.userService.core.repositories.UserRepository;
import ms.services.userService.core.services.impl.UserServiceImpl;
import ms.services.userService.rest.mvc.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = AppConfigurationUserDB.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = AppConfigurationUserDB.TRANSACTION_MANAGER_REF,
        basePackageClasses = {UserRepository.class, User.class})
@ComponentScan(basePackageClasses = {UserController.class, UserServiceImpl.class})
@BaseJdbcConfiguration(prefix = "datasource.user", persistanceUnitName = "userPersistanceUnit")
public class AppConfigurationUserDB implements Logger {

    public static final String ENTITY_MANAGER_FACTORY_REF = "userEntityManagerFactory";
    public static final String TRANSACTION_MANAGER_REF = "userTransactionManager";

    @Autowired
    private BaseJdbcFactoryBean jdbcFactoryBean;

    @PostConstruct
    private void postConstruct() {
        trace("User AppConfigurationUserDB");
    }

    @Bean(name = "userDataSource")
    public DataSource dataSource(){
        return jdbcFactoryBean.getDataSource(AppConfigurationUserDB.class);
    }

    @Bean(name = AppConfigurationUserDB.ENTITY_MANAGER_FACTORY_REF)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        return jdbcFactoryBean.getEntityManagerFactoryBean(AppConfigurationUserDB.class);
    }

    @Bean(name = AppConfigurationUserDB.TRANSACTION_MANAGER_REF)
    public JpaTransactionManager transactionManager(){
        return jdbcFactoryBean.getJpaTransactionManager(AppConfigurationUserDB.class);
    }

}
