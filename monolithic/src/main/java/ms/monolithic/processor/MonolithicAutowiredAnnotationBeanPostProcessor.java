package ms.monolithic.processor;

import ms.commons.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

/**
 * Created by davor on 25/04/15.
 */
public class MonolithicAutowiredAnnotationBeanPostProcessor extends AutowiredAnnotationBeanPostProcessor implements Logger {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        trace("Autowire: " + beanName);
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }
}
