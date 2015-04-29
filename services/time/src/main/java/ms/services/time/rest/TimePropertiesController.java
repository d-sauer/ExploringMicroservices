package ms.services.time.rest;

import ms.services.time.properties.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by davor on 23/04/15.
 */
@RestController
public class TimePropertiesController {

    @Autowired
    @Qualifier("timeServiceProperties")
    private ServiceProperties properties;

    @RequestMapping(value = "/time/properties", method = { RequestMethod.GET }, produces = { "application/json" })
    public ServiceProperties getCalculatorProeprties() {
        return properties;
    }

}
