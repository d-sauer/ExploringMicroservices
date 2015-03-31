package ms.api.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

/**
 * Extends this class to enable deploying (WAR, EAR) to another servlet container (Tomcat, WebSphere, GlassFish, etc.). 
 * Or extends {@link SpringBootServletInitializer} and implements {@link WebApplicationInitializer}
 *  
 * @author davor
 *
 */
@Configuration
@ComponentScan
@AutoConfigureBefore
public class Microservice extends SpringBootServletInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(Microservice.class);
    
    public static ApplicationContext run(Class<?> clazz, String... args) {
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder();
        if (clazz != Microservice.class) {
            log.trace("Register {}", Microservice.class);
            appBuilder.sources(Microservice.class);
        }
        
        log.trace("Register {}", clazz);
        appBuilder.sources(clazz);

        return appBuilder.run(args);
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Servlet STARTUP context {}", servletContext.getContextPath());
        super.onStartup(servletContext);
    }
}
