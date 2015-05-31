package ms.api.service.autoconfig.database;

import ms.api.service.util.database.BaseDataSourceProperties;
import ms.api.service.util.database.DatabaseUtils;
import ms.commons.logging.Logger;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by davor on 31/05/15.
 */
@Component
public class BaseDataSourceFactory implements Logger {

    private Map<BaseDataSourceProperties, DataSource> registeredDataSources = new HashMap<>();

    public DataSource getDatasoDataSource(BaseDataSourceProperties properties) {

        for (Map.Entry<BaseDataSourceProperties, DataSource> entry : registeredDataSources.entrySet()) {
            BaseDataSourceProperties entryProperties = entry.getKey();

            if (entryProperties.getUrl().equals(properties.getUrl())
                    && entryProperties.getDriverClassName().equals(properties.getDriverClassName())
                    && entryProperties.getUsername().equals(properties.getUsername())) {

                debug("Load existing DataSource with properties {},", entryProperties);
                return entry.getValue();
            }
        }

        return registerDataSource(properties);
    }


    private DataSource registerDataSource(BaseDataSourceProperties properties) {
        debug("Create new datasource from properties: {}", properties);

        DataSource dataSource = DatabaseUtils.createDataSource(properties);
        registeredDataSources.put(properties, dataSource);

        return dataSource;
    }

}
