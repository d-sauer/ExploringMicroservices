package ms.api.service.util.database;

import ms.api.service.autoconfig.database.BaseJpaDataSourceProperties;
import ms.commons.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Created by davor on 27/05/15.
 *
 * @see BaseJpaDataSourceProperties
 */
@DependsOn("baseJpaDataSourceProperties")
public class BaseDataSourceProperties {

    @Autowired
    private BaseJpaDataSourceProperties baseJpaProperties;

    private String username;

    private String password;

    private String driverClassName;

    private String url;

    private Map<String, Object> jpa = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Merget JPA properties from 'sprin.jpa' and this property set under 'jpa' segment.

     * <br/>
     * <pre>
     * spring:
     *      jpa:
     *          propertyA: valueA
     *
     * datasource:
     *      customdatasource:
     *          jpa:
     *              propertyA: overridden_valueA
     * </pre>
     *
     * @return
     */
    public Properties getJpaProperties() {
        Map<String, Object> flatPropertiesMap = new HashMap<>();
        // Flat spring.jpa properties
        if (baseJpaProperties != null) {
            flatPropertiesMap.putAll(PropertyUtils.flatPropertiesMap(baseJpaProperties.getJpa()));
        }

        // Flat from 'jpa' segment
        flatPropertiesMap.putAll(PropertyUtils.flatPropertiesMap(jpa));


        Properties flatProperties = new Properties();
        flatProperties.putAll(flatPropertiesMap);
        return flatProperties;
    }

    public Map<String, Object> getJpa() {
        return this.jpa;
    }

    public void setJpa(Map<String, Object> jpa) {
        this.jpa = jpa;
    }



    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add("url='" + (url != null ? url : "") + "'");
        joiner.add("driverClassName='" + (driverClassName != null ? driverClassName : "") + "'");
        joiner.add("username='" + (username != null ? username : "") + "'");
        joiner.add("password='" + (password != null ? "***" : "") + "'");

        return joiner.toString();
    }

}
