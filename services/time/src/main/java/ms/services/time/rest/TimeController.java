package ms.services.time.rest;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import ms.commons.logging.Logger;
import ms.services.time.dto.DateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController implements Logger {

    @PostConstruct
    private void postConstruct() {
        debug("Register controller");
    }
    
    @RequestMapping(value = "/time", method = { RequestMethod.GET }, produces = { "application/json" })
    public DateTime getCurrentTime() {
        trace("Request: Get Current time");
        LocalDateTime now = LocalDateTime.now();
        DateTime datetime = new DateTime();
        datetime.setDateTime(now);
        
        return datetime;
    }
}
