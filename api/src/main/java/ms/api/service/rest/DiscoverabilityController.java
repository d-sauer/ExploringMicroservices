package ms.api.service.rest;

import ms.api.service.dto.DiscoverabilityData;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoverabilityController {

    @RequestMapping("/discover")
    public DiscoverabilityData discover() {
        DiscoverabilityData discover = new DiscoverabilityData();
        
        
        return discover;
    }
    
}
