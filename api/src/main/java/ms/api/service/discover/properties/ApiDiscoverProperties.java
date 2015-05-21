package ms.api.service.discover.properties;

import ms.commons.logging.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by davor on 23/04/15.
 */
@Component
@ConfigurationProperties(prefix = "api.discover")
public class ApiDiscoverProperties implements Logger {

    private String name;

    private String version;

    private String description;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

