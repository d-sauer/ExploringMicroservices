package ms.monolithic;

import ms.api.service.EnableCxp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan(basePackageClasses = { ms.monolithic.MonolithicConfiguration.class })
@EnableCxp
public class MonolithicApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(MonolithicApplication.class);

    public static void main(String ... args) {
        ApplicationContext appCtx = SpringApplication.run(MonolithicApplication.class, args);

        if (log.isTraceEnabled()) {
            for (String beanName : appCtx.getBeanDefinitionNames()) {
                Object bean = appCtx.getBean(beanName);
                String beanClassName = "-?-";
                try {
                    beanClassName = bean.getClass().getName();
                    if (bean.getClass().getPackage().getName().startsWith("ms")) {
                        log.trace("ms. Bean: {} ({})", beanName, beanClassName);
                    }
                } catch (Exception e) {
                    log.warn("Error reading bean {}", beanName);
                }
            }

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
