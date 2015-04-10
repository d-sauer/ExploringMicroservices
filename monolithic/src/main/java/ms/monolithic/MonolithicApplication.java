package ms.monolithic;

import ms.api.service.Microservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ms.monolithic.MonolithicConfiguration.class })
public class MonolithicApplication extends Microservice {
    
    public static void main(String[] args) {
        Microservice.run(MonolithicApplication.class, args);
    }

}
