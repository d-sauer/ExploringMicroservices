package ms.services.calculator.properties;

import ms.api.service.MicroserviceNamespace;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by davor on 29/04/15.
 */
@Component("calculatorCustomProperties")
@PropertySource("classpath:custom.properties")
@ConfigurationProperties("custom")
@MicroserviceNamespace
public class CustomProperties {

    private String property1;

    private String property2;

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
}
