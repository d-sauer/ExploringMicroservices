package ms.monolithic.properties;

import ms.commons.logging.Logger;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;
import java.util.Properties;

/**
 * Created by davor on 26/04/15.
 */
public class JarPropertiesPropertySource extends MapPropertySource implements Logger {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public JarPropertiesPropertySource(String name, Properties source) {
        super(name, (Map) source);
    }

    protected JarPropertiesPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

}
