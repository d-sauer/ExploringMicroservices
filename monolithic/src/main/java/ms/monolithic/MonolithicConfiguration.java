package ms.monolithic;

import javax.annotation.PostConstruct;

import ms.api.service.ServiceType;
import ms.commons.logging.Logger;
import ms.monolithic.impl.ServiceTypeImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

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

}
