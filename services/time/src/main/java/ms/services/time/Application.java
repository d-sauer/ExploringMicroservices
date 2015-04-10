package ms.services.time;

import ms.api.service.Microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends Microservice {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String [] args) {
        log.debug("Time application");
        Microservice.run(Application.class, args);
    }
    
}


