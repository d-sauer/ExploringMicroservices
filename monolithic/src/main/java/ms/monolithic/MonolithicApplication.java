package ms.monolithic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackageClasses = { ms.monolithic.MonolithicConfiguration.class })
public class MonolithicApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(MonolithicApplication.class);

    public static void main(String ... args) {
        ApplicationContext appCtx = SpringApplication.run(MonolithicApplication.class, args);

        if (log.isTraceEnabled()) {
            Arrays.asList(appCtx.getBeanDefinitionNames())
                    .stream()
                    .filter(bn -> appCtx.getBean(bn).getClass().getPackage().getName().startsWith("ms"))
                    .collect(Collectors.toList())
                    .forEach(b -> log.trace("Bean: " + b + " ({})", appCtx.getBean((String) b).getClass().getName()));
        }

        log.info("Bean count: {}", appCtx.getBeanDefinitionCount());
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
