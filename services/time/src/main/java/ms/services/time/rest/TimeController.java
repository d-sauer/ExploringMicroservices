package ms.services.time.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    @RequestMapping(name = "/time", method = { RequestMethod.GET }, produces = { "application/json" })
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }
}
