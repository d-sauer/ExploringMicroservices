package ms.services.shared.rest;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import ms.commons.logging.Logger;
import ms.services.shared.dto.DiscoverData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiscoverController implements Logger {

    @Autowired
    private ServletContext servletContext;
    
    private DiscoverData discoverData;
    
    @PostConstruct
    private void postConstruct() {
        debug("Register SHARED controller");
        discoverData = new DiscoverData(servletContext);
    }
    
    @RequestMapping("/discover")
    public DiscoverData discover() {
        return discoverData;
    }
    
}
