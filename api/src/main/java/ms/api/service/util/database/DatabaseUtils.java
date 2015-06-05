package ms.api.service.util.database;

import ms.commons.util.PackageUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by davor on 27/05/15.
 */
public class DatabaseUtils {

    public static DataSource createDataSource(Properties properties){
        Object url = properties.get("url");
        Object driverClassName = properties.getProperty("driver-class-name");
        Object username = properties.getProperty("username");
        Object password = properties.getProperty("password");

        DataSourceBuilder builder = DataSourceBuilder.create();

        if (url != null) {
            builder.url(url.toString());
        }

        if (driverClassName != null) {
            builder.driverClassName(driverClassName.toString());
        }

        if (username != null) {
            builder.username(username.toString());
        }

        if (password != null) {
            builder.password(password.toString());
        }

        return builder.build();
    }

    public static DataSource getJndiDataSource(String jndiName) {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(jndiName);
        return dataSource;
    }

    public static LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean(String persistanceName, DataSource dataSource, Properties jpaProperties, Class<?>[] entitiePackages) {
        return createEntityManagerFactoryBean(persistanceName, dataSource, jpaProperties, PackageUtils.getPackageNames(entitiePackages));
    }

    public static LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean(String persistanceName, DataSource dataSource, Properties jpaProperties, String[] entitiePackages){
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(entitiePackages);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(jpaProperties);
        em.setPersistenceUnitName(persistanceName);

        return em;
    }

    public static JpaTransactionManager createJpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
