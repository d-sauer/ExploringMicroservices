package ms.api.service.rest;

import javax.servlet.ServletContext;

import ms.api.service.dto.DiscoverabilityData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoverabilityController {
    
    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/discover")
    public DiscoverabilityData discover() {
        DiscoverabilityData discover = new DiscoverabilityData();
        
        discover.setServletInfo(servletContext.getServerInfo());
        
        return discover;
    }
    
}
