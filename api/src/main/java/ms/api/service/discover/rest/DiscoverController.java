package ms.api.service.discover.rest;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import ms.api.service.build.ServiceBuildType;
import ms.api.service.discover.dto.DiscoverData;
import ms.api.service.discover.properties.ApiDiscoverProperties;
import ms.commons.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController implements Logger {

    @Autowired
    private ServletContext servletContext;

    @Autowired(required = false)
    private ServiceBuildType serviceBuildType;

    @Autowired
    private ApiDiscoverProperties apiDiscoverProperties;
    
    private DiscoverData discoverData;
    
    @PostConstruct
    private void postConstruct() {
        trace("Register discover controller");
        discoverData = new DiscoverData(servletContext, serviceBuildType, apiDiscoverProperties);
    }
    
    @RequestMapping
    public DiscoverData discover() {
        return discoverData;
    }
    
}
