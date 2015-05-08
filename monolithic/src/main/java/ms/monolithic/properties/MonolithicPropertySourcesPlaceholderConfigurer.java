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
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.util.*;

/**
 * Created by davor on 23/04/15.
 */
public class MonolithicPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer implements Logger {

    public static final String NAMESPACE_SEPARATOR = ".";

    private static final String[] JAR_PROPERTY_FILES = {"application.properties", "application.yml", "application.yaml"};

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
                            String newPrefix = scope + NAMESPACE_SEPARATOR + configurationProperties.prefix();
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
        final List<JarPropertiesPropertySource> jppsl = new ArrayList<>();

        microserviceJar.entrySet().forEach(microserviceJar -> {
            JarDetail detail = microserviceJar.getValue();
            try {
                for (String propertyFile : JAR_PROPERTY_FILES) {
                    if (JarUtils.exists(detail, propertyFile)) {
                        Properties jarProps = loadJarProperties(detail, propertyFile);

                        Properties namespaceProperties = createJarNamespaceProperties(microserviceJar.getKey(), jarProps);
                        JarPropertiesPropertySource jpps = new JarPropertiesPropertySource("jar-prop-" + microserviceJar.getKey(), namespaceProperties);
                        jppsl.add(jpps);

                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        PropertySources ps = super.getAppliedPropertySources();
        ps.forEach(propSource -> mps.addLast(new MonolithicPropertySourceProxy(propSource)));

        jppsl.forEach(jpps -> mps.addLast(new MonolithicPropertySourceProxy(jpps)));

        return mps;
    }


    private Properties loadJarProperties(JarDetail detail, String propertyFile) throws IOException {
        if (propertyFile.endsWith(".properties")) {
            return JarUtils.getPropertyFile(detail, propertyFile);
        } else {
            Properties jarProps = new Properties();

            InputStreamResource inputStreamResource = new InputStreamResource(JarUtils.getInputStream(detail, propertyFile));
            YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
            PropertySource source = loader.load("jarProperties", inputStreamResource, null);

            if (source instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource)source;
                mapPropertySource.getSource().entrySet().forEach(entry -> jarProps.setProperty(entry.getKey(), entry.getValue().toString()));
            }

            return jarProps;
        }
    }

    private Properties createJarNamespaceProperties(String namespace, Properties properties) {
        Properties newProps = new Properties();
        properties.entrySet().forEach(jarPropEntry -> {
            newProps.setProperty(namespace + NAMESPACE_SEPARATOR + jarPropEntry.getKey().toString(), jarPropEntry.getValue().toString());
        });

        return newProps;
    }

}
