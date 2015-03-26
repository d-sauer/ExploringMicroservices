package ms.services.time;

import ms.api.service.Microservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ms.services.time.rest.TimeController.class })
public class Application extends Microservice {

    public static void main(String [] args) {
        Microservice.run(Application.class, args);
    }
    
}


