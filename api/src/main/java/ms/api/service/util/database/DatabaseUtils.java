package ms.api.service.util.database;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by davor on 27/05/15.
 */
public class DatabaseUtils {

    public static DataSource createDataSource(BaseDataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClass().getClassLoader())
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    public static DataSource getJndiDataSource(String jndiName) {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(jndiName);
        return dataSource;
    }

}
