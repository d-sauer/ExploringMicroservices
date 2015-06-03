package ms.api.service.util.database;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by davor on 02/06/15.
 */
public class BaseDataSourceHolder {

    private BaseDataSourceProperties dataSourceProperties;

    private DataSource dataSource;

    private static final Map<String, LocalContainerEntityManagerFactoryBean> entityManagerFactoryBeanCache = new HashMap<>();

    private static final Map<String, EntityManager> entityManagerCache = new HashMap<>();

    private static final Map<String, JpaTransactionManager> jpaTransactionManagerCache = new HashMap<>();


    public BaseDataSourceHolder(BaseDataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }


    public BaseDataSourceProperties getDataSourceProperties() {
        return dataSourceProperties;
    }

    public DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = DatabaseUtils.createDataSource(dataSourceProperties);
        }
        return dataSource;
    }

    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(String entityManagerFactoryRef, String persistanceName, String[] entitiePackages) {
        String key = entityManagerFactoryRef + "_" + persistanceName + "_" + Arrays.toString(entitiePackages);
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = this.entityManagerFactoryBeanCache.get(key);
        if (entityManagerFactoryBean == null) {
            entityManagerFactoryBean = DatabaseUtils.createEntityManagerFactoryBean(persistanceName, getDataSource(), getDataSourceProperties().getJpaProperties(), entitiePackages);
            this.entityManagerFactoryBeanCache.put(key, entityManagerFactoryBean);
        }
        return entityManagerFactoryBean;
    }

    public EntityManager getEntityManager(String entityManagerFactoryRef, String persistanceName, String[] entitiePackages) {
        String key = entityManagerFactoryRef + "_" + persistanceName + "_" + Arrays.toString(entitiePackages);
        EntityManager entityManager = entityManagerCache.get(key);
        if (entityManager == null) {
            entityManager = DatabaseUtils.entityManager(getEntityManagerFactoryBean(entityManagerFactoryRef, persistanceName, entitiePackages).getObject());
            entityManagerCache.put(key, entityManager);
        }
        return entityManager;
    }

    public JpaTransactionManager getJpaTransactionManager(String entityManagerFactoryRef, String persistanceName, String[] entitiePackages) {
        String key = entityManagerFactoryRef + "_" + persistanceName + "_" + Arrays.toString(entitiePackages);
        JpaTransactionManager jpaTransactionManager = jpaTransactionManagerCache.get(key);

        if (jpaTransactionManager == null) {
            jpaTransactionManager = DatabaseUtils.createJpaTransactionManager(getEntityManagerFactoryBean(entityManagerFactoryRef, persistanceName, entitiePackages).getObject());
            jpaTransactionManagerCache.put(key, jpaTransactionManager);
        }

        return jpaTransactionManager;
    }

}
