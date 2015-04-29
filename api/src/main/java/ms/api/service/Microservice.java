package ms.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        SpringApplicationBuilder appBuilder = build(clazz);
        ApplicationContext appCtx = appBuilder.run(args);
        // List only beans from project scope, only from 'ms.*' package
        if (log.isTraceEnabled()) {
            Arrays.asList(appCtx.getBeanDefinitionNames())
                    .stream()
                    .filter(bn -> appCtx.getBean(bn).getClass().getPackage().getName().startsWith("ms"))
                    .collect(Collectors.toList())
                    .forEach(b -> log.trace("Bean: " + b + " ({})", appCtx.getBean((String)b).getClass().getName()) );
        }

        log.info("Bean count: {}", appCtx.getBeanDefinitionCount());

        return appCtx;
    }

    public static SpringApplicationBuilder build(Class<?> clazz) {
        log.trace("Build application");
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder();
        if (clazz != Microservice.class) {
            appBuilder.sources(Microservice.class);
        }
        appBuilder.sources(clazz);

        return appBuilder;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ApplicationContext parent = getExistingRootWebApplicationContext(servletContext);
        if (parent == null) {
            log.info("Servlet STARTUP context {}", servletContext.getContextPath());
            super.onStartup(servletContext);
        } 
    }
    
    @Override
    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        log.trace("createRootApplicationContext");
        ApplicationContext parent = getExistingRootWebApplicationContext(servletContext);
        
        if (parent == null) {
            log.trace("Create root application context");
            return super.createRootApplicationContext(servletContext);
        }
        
        return null;
    }

    
    private ApplicationContext getExistingRootWebApplicationContext(ServletContext servletContext) {
        Object context = servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        if (context instanceof ApplicationContext) {
            return (ApplicationContext) context;
        }
        return null;
    }

}
