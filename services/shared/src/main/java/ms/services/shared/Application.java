package ms.services.shared;

import ms.api.service.Microservice;

public class Application  extends Microservice {

    public static void main(String [] args) {
        Microservice.run(Application.class, args);
    }
    
}
