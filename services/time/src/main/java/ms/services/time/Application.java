package ms.services.time;

import ms.api.service.Microservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends Microservice {

    public static void main(String [] args) {
        Microservice.run(Application.class, args);
    }
    
}


