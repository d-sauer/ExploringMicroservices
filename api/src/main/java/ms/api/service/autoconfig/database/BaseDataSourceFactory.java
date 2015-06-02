package ms.api.service.autoconfig.database;

import ms.api.service.util.database.BaseDataSourceHolder;
import ms.api.service.util.database.BaseDataSourceProperties;
import ms.commons.logging.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davor on 31/05/15.
 */
@Component
public class BaseDataSourceFactory implements Logger {

    private Map<BaseDataSourceProperties, BaseDataSourceHolder> registeredDataSources = new HashMap<>();

    public BaseDataSourceHolder get(BaseDataSourceProperties properties) {
        for (Map.Entry<BaseDataSourceProperties, BaseDataSourceHolder> entry : registeredDataSources.entrySet()) {
            BaseDataSourceProperties entryProperties = entry.getKey();

            if (entryProperties.getUrl().equals(properties.getUrl())
                    && entryProperties.getDriverClassName().equals(properties.getDriverClassName())
                    && entryProperties.getUsername().equals(properties.getUsername())) {

                debug("Load existing DataSource with properties {},", entryProperties);
                return entry.getValue();
            }
        }

        return createHolder(properties);
    }

    private BaseDataSourceHolder createHolder(BaseDataSourceProperties properties) {
        debug("Create new DataSource from properties: {}", properties);

        BaseDataSourceHolder dataSourceHolder = new BaseDataSourceHolder(properties);
        registeredDataSources.put(properties, dataSourceHolder);

        return dataSourceHolder;
    }

}
