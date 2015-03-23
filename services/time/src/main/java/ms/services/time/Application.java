package ms.services.time;

import java.util.Arrays;

import ms.commons.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements Logger {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        org.slf4j.Logger log = Logger.log(Application.class);
        
        if (log.isDebugEnabled()) {
            // List beans provided by Spring Boot
            String[] beans = ctx.getBeanDefinitionNames();
            Arrays.sort(beans);
            for (String bean : beans)
                log.debug("Bean: {}", bean);
        }
    }

    
}


