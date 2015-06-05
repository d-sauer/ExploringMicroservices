package ms.api.service.autoconfig.jdbc;

import ms.api.service.util.database.DatabaseUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by davor on 04/06/15.
 */
public class BaseJdbcHolder {

    private Properties properties;

    private BaseJdbcEntityManagerProperties entityManagerProperties;

    private DataSource dataSource;

    public BaseJdbcHolder(Properties properties) {
        this.properties = properties;
    }

    public DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = DatabaseUtils.createDataSource(properties);
        }

        return dataSource;
    }


}
