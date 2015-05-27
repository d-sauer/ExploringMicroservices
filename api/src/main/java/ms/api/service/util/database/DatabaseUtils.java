package ms.api.service.util.database;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * Created by davor on 27/05/15.
 */
public class DatabaseUtils {

    public static DataSource createDataSource(BaseDataSourceProperties properties, Logger logger){
        logger.debug("Create datasource with properties: {}", properties);
        return DataSourceBuilder.create(properties.getClass().getClassLoader())
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

}
