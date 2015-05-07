package ms.monolithic.properties;

import ms.api.service.util.AnnotationUtils;
import ms.api.service.MicroserviceNamespace;
import ms.api.service.util.MicroserviceNamespaceUtils;
import ms.commons.jar.JarDetail;
import ms.commons.jar.JarUtils;
import ms.commons.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.io.IOException;
import java.util.*;

/**
 * Created by davor on 23/04/15.
 */
public class MonolithicPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer implements Logger {


    private Map<String, JarDetail> microserviceJar = new HashMap<>();


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        trace("bean Factory:" + beanFactory.getBeanDefinitionCount());

        Iterator<String> it = beanFactory.getBeanNamesIterator();
        while (it.hasNext()) {
            String beanName = it.next();
            try {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                if (beanDefinition.getBeanClassName() != null) {
                    Class beanClass = Class.forName(beanDefinition.getBeanClassName());
                    if (beanClass != null && beanClass.isAnnotationPresent(MicroserviceNamespace.class)) {
                        String scope = MicroserviceNamespaceUtils.getScope(beanClass);
                        trace("beanName:" + beanName + ", class:" + beanDefinition.getBeanClassName() + ", scope:" + scope);

                        microserviceJar.put(scope, new JarDetail(beanClass));

                        if (beanClass.isAnnotationPresent(ConfigurationProperties.class)) {
                            ConfigurationProperties configurationProperties = (ConfigurationProperties) beanClass.getAnnotation(ConfigurationProperties.class);
                            String newPrefix = scope + "|" + configurationProperties.prefix();
                            AnnotationUtils.changeAnnotationValue(configurationProperties, "prefix", newPrefix);
                        }
                    }
                }
            } catch (Exception e) {
                // Can't get bean by name, ignore and move to the next bean
            }
        }

        super.postProcessBeanFactory(beanFactory);
    }


    @Override
    public PropertySources getAppliedPropertySources() throws IllegalStateException {
        final MutablePropertySources mps = new MutablePropertySources();
        final HashMap<String, Set<String>> propertyNameInMicroservices = new HashMap<>();

        final List<JarPropertiesPropertySource> jppsl = new ArrayList<>();

        microserviceJar.entrySet().forEach(microserviceJar -> {
            JarDetail detail = microserviceJar.getValue();
            try {
                Properties jarProps = JarUtils.getPropertyFile(detail, "application.properties");
                Properties newProps = new Properties();
                jarProps.entrySet().forEach(jarPropEntry -> {
                    newProps.setProperty(microserviceJar.getKey() + "|" + jarPropEntry.getKey().toString(), jarPropEntry.getValue().toString());

                    Set<String> microservices = propertyNameInMicroservices.get(jarPropEntry.getKey().toString());
                    if (microservices == null) {
                        microservices = new HashSet<String>();
                        microservices.add(microserviceJar.getKey().toString());

                        propertyNameInMicroservices.put(jarPropEntry.getKey().toString(), microservices);
                    } else {
                        microservices.add(microserviceJar.getKey().toString());
                    }
                });


                JarPropertiesPropertySource jpps = new JarPropertiesPropertySource("jar-prop-" + microserviceJar.getKey(), newProps);
                jppsl.add(jpps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        PropertySources ps = super.getAppliedPropertySources();
        ps.forEach(propSource -> mps.addLast(new MonolithicPropertySourceProxy(propSource)));

        jppsl.forEach(jpps -> mps.addLast(new MonolithicPropertySourceProxy(jpps)));

        return mps;
    }

}
