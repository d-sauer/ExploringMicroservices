package ms.api.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ms.api.service.rest.DiscoverabilityController.class })
public class Microservice extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        run(Microservice.class, args);
    }

    public static ApplicationContext run(Class<?> clazz, String... args) {
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder();
        if (clazz != Microservice.class)
            appBuilder.sources(Microservice.class);

        appBuilder.sources(clazz);

        return appBuilder.run(args);
    }
}
