package ms.services.userService;

import ms.api.service.MicroserviceNamespace;
import ms.commons.logging.Logger;
import ms.services.userService.core.model.entities.User;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = { ms.services.userService.core.services.impl.UserServiceImpl.class })
@EntityScan(basePackageClasses = { ms.services.userService.core.model.entities.User.class })
public class AppConfiguration implements Logger {

    @Bean
    @MicroserviceNamespace
    @ConfigurationProperties(prefix="datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }


    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userDataSource())
                .packages(User.class)
                .persistenceUnit("user")
                .build();
    }

}
