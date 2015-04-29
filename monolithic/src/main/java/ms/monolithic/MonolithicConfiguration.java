package ms.monolithic;

import ms.api.service.ServiceType;
import ms.commons.logging.Logger;
import ms.monolithic.impl.ServiceTypeImpl;
import ms.monolithic.processor.MonolithicBeanPostProcessor;
import ms.monolithic.properties.MonolithicPropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ComponentScan(basePackageClasses = { ms.monolithic.MonolithicConfiguration.class })
public class MonolithicConfiguration implements Logger {

    
    @PostConstruct
    private void postConstruct() {
        trace("Register monolithic configuration");
    }

    @Bean
    public ServiceType serviceType() {
        trace("Register Bean {}", ServiceTypeImpl.class.getName());
        return new ServiceTypeImpl();
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new MonolithicBeanPostProcessor();
    }

//    @Bean
//    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
//        return new MonolithicBeanFactoryPostProcessor();
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new MonolithicPropertySourcesPlaceholderConfigurer();
    }

//    @Bean
//    public AutowiredAnnotationBeanPostProcessor autowire() {
//        return new MonolithicAutowiredAnnotationBeanPostProcessor();
//    }

}
