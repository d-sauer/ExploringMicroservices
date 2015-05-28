package ms.api.service.autoconfig.database;

import ms.api.service.util.database.BaseDataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davor on 27/05/15.
 */

/**
 * Generic replacement for {@link org.springframework.boot.autoconfigure.orm.jpa.JpaProperties}. <br/>
 * User in combination with {@link BaseDataSourceProperties}
 *
 * @see ms.api.service.autoconfig.CxpAutoConfiguration
 */
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