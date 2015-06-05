package ms.api.service.autoconfig.jdbc;

import ms.api.service.util.database.DatabaseUtils;
import ms.commons.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedNames;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by davor on 04/06/15.
 */
@Component
public class BaseJdbcFactoryBean implements Logger {

    @Autowired
    private Environment env;

    private Map<Properties, DataSource> registeredDataSources = new HashMap<>();
    private Map<Class, LocalContainerEntityManagerFactoryBean> registeresEntityManagerFactoryBean = new HashMap<>();
    private Map<Class, JpaTransactionManager> registeresJpaTransactionManager = new HashMap<>();

    public DataSource getDataSource(Class<?> configurationClass) {
        final String propertyPrefix = extractPrefix(configurationClass);
        Properties properties = loadConfigurationProperties(propertyPrefix);

        String url = (String) properties.get("url");
        String username = (String) properties.get("username");

        for (Map.Entry<Properties, DataSource> entry : registeredDataSources.entrySet()) {
            Properties entryProperties = entry.getKey();

            String entryUrl = entryProperties.getProperty("url");
            String entryUsername = entryProperties.getProperty("username");

            if (Objects.equals(url, entryUrl) && Objects.equals(username, entryUsername)) {
                debug("Load DataSource based on: URL: {}, usename: {}", url, username);
                return entry.getValue();
            }
        }

        debug("Create DataSource, from properties '{}': {}", propertyPrefix, properties);
        DataSource dataSource = DatabaseUtils.createDataSource(properties);
        registeredDataSources.put(properties, dataSource);
        return dataSource;
    }


    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(Class<?> configurationClass) {
        LocalContainerEntityManagerFactoryBean bean = registeresEntityManagerFactoryBean.get(configurationClass);

        if (bean == null) {
            debug("Create {} for configuration {}", LocalContainerEntityManagerFactoryBean.class, configurationClass);

            BaseJdbcEntityManagerProperties entityManagerProperties = BaseJdbcEntityManagerProperties.create(configurationClass);
            Properties jpaProperties = loadConfigurationProperties(extractPrefix(configurationClass) + ".jpa");

            String persistanceName = entityManagerProperties.getPersistanceUnitName();
            DataSource dataSource = getDataSource(configurationClass);
            String[] entityPackages = entityManagerProperties.getBasePackages();
            bean = DatabaseUtils.createEntityManagerFactoryBean(persistanceName, dataSource, jpaProperties, entityPackages);

            registeresEntityManagerFactoryBean.put(configurationClass, bean);
        } else {
            debug("Load {} for configuration {}", LocalContainerEntityManagerFactoryBean.class, configurationClass);
        }

        return bean;
    }


    public JpaTransactionManager getJpaTransactionManager(Class<?> configurationClass) {
        JpaTransactionManager jpaTransactionManager = registeresJpaTransactionManager.get(configurationClass);

        if (jpaTransactionManager == null) {
            debug("Create {} for configuration {}", JpaTransactionManager.class, configurationClass);
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = getEntityManagerFactoryBean(configurationClass);
            jpaTransactionManager = DatabaseUtils.createJpaTransactionManager(entityManagerFactoryBean.getObject());

            registeresJpaTransactionManager.put(configurationClass, jpaTransactionManager);
        } else {
            debug("Load {} for configuration {}", JpaTransactionManager.class, configurationClass);
        }

        return jpaTransactionManager;
    }


    private Properties loadConfigurationProperties(String configurationPropertiesPrefix) {
        if (configurationPropertiesPrefix != null) {
            return getRelaxedEnvironmentProperties(configurationPropertiesPrefix);
        } else {
            throw new RuntimeException("For using " + this.getClass().getName() + ", you need to annotate configuration class with " + ConfigurationProperties.class.getName());
        }
    }


    private Properties getRelaxedEnvironmentProperties(String prefix) {
        final int prefixLength = prefix.length() + 1;
        Properties properties = new Properties();

        for (Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof EnumerablePropertySource) {
                EnumerablePropertySource mapPropertySource = (EnumerablePropertySource) propertySource;
                for (String propertyName : mapPropertySource.getPropertyNames()) {
                    if (propertyName.startsWith(prefix)) {
                        Object propertyValue = mapPropertySource.getProperty(propertyName);
                        propertyName = propertyName.substring(prefixLength);

                        RelaxedNames relaxedNames = new RelaxedNames(propertyName);
                        relaxedNames.forEach(name -> properties.put(name, propertyValue));
                    }
                }
            }
        }

        return properties;
    }


    private String extractPrefix(Class<?> type) {
        BaseJdbcConfiguration configuration = AnnotationUtils.findAnnotation(type, BaseJdbcConfiguration.class);
        if (configuration != null) {
            return StringUtils.hasLength(configuration.value()) ? configuration.value() : configuration.prefix();
        }

        return null;
    }

}
