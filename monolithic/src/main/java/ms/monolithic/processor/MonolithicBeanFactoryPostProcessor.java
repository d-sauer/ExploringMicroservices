package ms.monolithic.processor;

import ms.commons.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by davor on 25/04/15.
 */
public class MonolithicBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Logger {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        trace("Processing beanFactory, count:" + beanFactory.getBeanDefinitionCount());
    }


}
