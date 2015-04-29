package ms.services.calculator.properties;

import ms.api.service.MicroserviceNamespace;
import ms.commons.logging.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by davor on 23/04/15.
 */
@Component("calculatorServiceProperties")
@MicroserviceNamespace
@ConfigurationProperties(prefix = "service")
public class ServiceProperties implements Logger {

    private String name;

    private String property1;

    private String property2;

    private String property3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getClassName() {
        return this.getClass().getName();
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property3) {
        this.property3 = property3;
    }
}

