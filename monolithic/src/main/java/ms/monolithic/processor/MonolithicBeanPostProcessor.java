package ms.monolithic.processor;

import ms.api.service.MicroserviceNamespace;
import ms.api.service.util.MicroserviceNamespaceUtils;
import ms.commons.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by davor on 24/04/15.
 */
public class MonolithicBeanPostProcessor implements BeanPostProcessor, Logger {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(MicroserviceNamespace.class)) {
            MicroserviceNamespaceUtils.setAutoScope(bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
