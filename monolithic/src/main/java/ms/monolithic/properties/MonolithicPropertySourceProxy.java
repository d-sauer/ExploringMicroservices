package ms.monolithic.properties;

import ms.commons.logging.Logger;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

/**
 * Created by davor on 27/04/15.
 */
public class MonolithicPropertySourceProxy extends PropertySource implements Logger {

    private PropertySource propertySource;

    public MonolithicPropertySourceProxy(PropertySource propertySource) {
        super(propertySource.getName(), propertySource.getSource());
        Object o = propertySource.getSource();
        if (o instanceof StandardEnvironment) {
            ConfigurableEnvironment ce = (ConfigurableEnvironment) o;
            trace("Create property source proxy for:" + propertySource.getName() + ", with nested property sources: " + ce.getPropertySources());
            MutablePropertySources cemps = ce.getPropertySources();
            cemps.forEach(propSource -> {
                cemps.replace(propSource.getName(), new MonolithicPropertySourceProxy(propSource));
            });
        } else {
            trace("Create property source proxy for:" + propertySource.getName());
        }

        this.propertySource = propertySource;
    }


    @Override
    public Object getProperty(String name) {
        Object value = propertySource.getProperty(name);

        if (value == null) {
            int index = name.indexOf(MonolithicPropertySourcesPlaceholderConfigurer.NAMESPACE_SEPARATOR);
            if (index != -1) {
                name = name.substring(index + 1);
                value = propertySource.getProperty(name);
            }
        }

        return value;
    }

    @Override
    public boolean containsProperty(String name) {
        boolean containsProperty = propertySource.containsProperty(name);;

        if (containsProperty == false) {
            int index = name.indexOf(MonolithicPropertySourcesPlaceholderConfigurer.NAMESPACE_SEPARATOR);
            if (index != -1) {
                name = name.substring(index + 1);
                containsProperty = propertySource.containsProperty(name);
            }
        }

        return containsProperty;
    }

    @Override
    public String toString() {
        return getName();
    }
}
