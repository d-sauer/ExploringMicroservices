package ms.api.service.util.database;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * Created by davor on 02/06/15.
 */
public class BaseDataSourceHolder {

    private BaseDataSourceProperties dataSourceProperties;

    private DataSource dataSource;

    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    private EntityManager entityManager;

    private JpaTransactionManager jpaTransactionManager;


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

    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(String persistanceName, String [] entitiePackages) {
        if (entityManagerFactoryBean == null) {
            entityManagerFactoryBean = DatabaseUtils.createEntityManagerFactoryBean(persistanceName, getDataSource(), getDataSourceProperties().getJpaProperties(), entitiePackages);
        }
        return entityManagerFactoryBean;
    }

    public EntityManager getEntityManager(String persistanceName, String [] entitiePackages) {
        if (entityManager == null) {
            entityManager = DatabaseUtils.entityManager(getEntityManagerFactoryBean(persistanceName, entitiePackages).getObject());
        }
        return entityManager;
    }

    public JpaTransactionManager getJpaTransactionManager(String persistanceName, String[] entitiePackages) {
        if (jpaTransactionManager == null) {
            jpaTransactionManager = DatabaseUtils.createJpaTransactionManager(getEntityManagerFactoryBean(persistanceName, entitiePackages).getObject());
        }
        return jpaTransactionManager;
    }

}
