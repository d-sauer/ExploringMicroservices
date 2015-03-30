package ms.api.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class Microservice extends SpringBootServletInitializer implements WebApplicationInitializer, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(Microservice.class);
    
    public static ApplicationContext run(Class<?> clazz, String... args) {
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder();
        if (clazz != Microservice.class) {
            log.info("Register {}", Microservice.class);
            appBuilder.sources(Microservice.class);
        }
        
        log.info("Register {}", clazz);
        appBuilder.sources(clazz);

        return appBuilder.run(args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Application context, app name {}" , applicationContext.getApplicationName());
        Arrays.sort(applicationContext.getBeanDefinitionNames());
        for(String bean : applicationContext.getBeanDefinitionNames()) {
            log.info("App beans: {}", bean);
        }
        
    }
}
