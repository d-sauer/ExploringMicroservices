package ms.commons.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davor on 27/05/15.
 */
public class PropertyUtils {

    public static Map<String, Object> flatPropertiesMap(Map<String, Object> propertiesMap) {
        return flatPropertiesMap("", propertiesMap);
    }

    public static Map<String, Object> flatPropertiesMap(String prefix, Map<String, Object> propertiesMap) {
        Map<String, Object> flatPropertiesMap = new HashMap<>();

        propertiesMap.entrySet().forEach(entry -> {
            if (entry.getValue() instanceof Map) {
                if (prefix != null && prefix.length() != 0) {
                    flatPropertiesMap.putAll(flatPropertiesMap(prefix + "." + entry.getKey(), (Map) entry.getValue()));
                } else {
                    flatPropertiesMap.putAll(flatPropertiesMap(entry.getKey(), (Map) entry.getValue()));
                }

            } else {
                if (prefix != null && prefix.length() != 0) {
                    flatPropertiesMap.put(prefix + "." + entry.getKey(), entry.getValue());
                } else {
                    flatPropertiesMap.put(entry.getKey(), entry.getValue());
                }
            }
        });

        return flatPropertiesMap;
    }


}
