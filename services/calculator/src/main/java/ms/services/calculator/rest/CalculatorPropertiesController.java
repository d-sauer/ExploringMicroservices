package ms.services.calculator.rest;

import ms.services.calculator.properties.CustomProperties;
import ms.services.calculator.properties.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davor on 23/04/15.
 */
@RestController
public class CalculatorPropertiesController {

    @Autowired
    @Qualifier("calculatorServiceProperties")
    private ServiceProperties serviceProperties;

    @Autowired
    @Qualifier("calculatorCustomProperties")
    private CustomProperties customProperties;

    @RequestMapping(value = "/calculator/properties", method = { RequestMethod.GET }, produces = { "application/json" })
    public List getCalculatorProeprties() {
        List properties = new ArrayList<>();
        properties.add(serviceProperties);
        properties.add(customProperties);

        return properties;
    }

}
