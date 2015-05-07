package ms.services.time;

import ms.api.service.EnableCxp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCxp
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String [] args) {
        log.debug("Time application");
        SpringApplication.run(Application.class, args);
    }
    
}


