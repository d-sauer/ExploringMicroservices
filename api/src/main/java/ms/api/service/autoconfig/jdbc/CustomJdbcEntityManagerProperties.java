package ms.api.service.autoconfig.jdbc;

import ms.commons.util.PackageUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by davor on 04/06/15.
 */
public class CustomJdbcEntityManagerProperties {

    private String [] basePackages;

    private String entityManagerFactoryRef;

    private String transactionManagerRef;

    private String persistanceUnitName;

    public String[] getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }

    public String getEntityManagerFactoryRef() {
        return entityManagerFactoryRef;
    }

    public void setEntityManagerFactoryRef(String entityManagerFactoryRef) {
        this.entityManagerFactoryRef = entityManagerFactoryRef;
    }

    public String getTransactionManagerRef() {
        return transactionManagerRef;
    }

    public void setTransactionManagerRef(String transactionManagerRef) {
        this.transactionManagerRef = transactionManagerRef;
    }

    public String getPersistanceUnitName() {
        return persistanceUnitName;
    }

    public void setPersistanceUnitName(String persistanceUnitName) {
        this.persistanceUnitName = persistanceUnitName;
    }

    public static CustomJdbcEntityManagerProperties create(Class<?> configurationClass) {
        CustomJdbcEntityManagerProperties properties = new CustomJdbcEntityManagerProperties();
        EnableJpaRepositories enableJpaRepositories = AnnotationUtils.findAnnotation(configurationClass, EnableJpaRepositories.class);
        if (enableJpaRepositories != null) {
            Set<String> basePackages = new HashSet<>();
            basePackages.addAll(Arrays.asList(enableJpaRepositories.basePackages()));
            basePackages.addAll(Arrays.asList(PackageUtils.getPackageNames(enableJpaRepositories.basePackageClasses())));
            properties.setBasePackages(basePackages.toArray(new String [] {}));

            properties.setEntityManagerFactoryRef(enableJpaRepositories.entityManagerFactoryRef());
            properties.setTransactionManagerRef(enableJpaRepositories.transactionManagerRef());
        }

        EnableCustomJdbcConfiguration configuration = AnnotationUtils.findAnnotation(configurationClass, EnableCustomJdbcConfiguration.class);
        if (configuration != null) {
            properties.setPersistanceUnitName(configuration.persistanceUnitName());
        }

        return properties;
    }
}
