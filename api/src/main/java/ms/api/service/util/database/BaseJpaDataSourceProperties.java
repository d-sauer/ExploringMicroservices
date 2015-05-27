package ms.api.service.util.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davor on 27/05/15.
 */

/**
 * Generic replacement for {@link org.springframework.boot.autoconfigure.orm.jpa.JpaProperties}. <br/>
 * User in combination with {@link BaseDataSourceProperties}
 *
 * @see ms.api.service.autoconfigure.CxpAutoConfiguration
 */
@Configuration
@ConfigurationProperties("spring")
public class BaseJpaDataSourceProperties {

    private Map<String, Object> jpa = new HashMap<>();

    public Map<String, Object> getJpa() {
        return jpa;
    }

    public void setJpa(Map<String, Object> jpa) {
        this.jpa = jpa;
    }
}
