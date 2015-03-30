package ms.services.shared;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ms.api.service.Microservice;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ms.services.shared.rest.DiscoverController.class })
public class Application extends Microservice {

    public static void main(String [] args) {
        Microservice.run(Application.class, args);
    }
    
}
