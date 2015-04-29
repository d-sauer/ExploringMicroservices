package ms.api.service.util;

import ms.api.service.MicroserviceNamespace;
import ms.commons.jar.JarDetail;

/**
 * Created by davor on 27/04/15.
 */
public class MicroserviceNamespaceUtils {

    public static String getScope(Object bean) {
        return getScope(bean.getClass());
    }

    public static String getScope(Class beanClass) {
        if (!beanClass.isAnnotationPresent(MicroserviceNamespace.class)) {
            return null;
        }

        MicroserviceNamespace scope = (MicroserviceNamespace) beanClass.getAnnotation(MicroserviceNamespace.class);
        String value = scope.value();
        if ("".equals(value)) {
            JarDetail detail = new JarDetail(beanClass);
            if (detail != null && detail.getName() != null) {
                value = detail.getName();
            }
        }

        return value;
    }

    public static void setAutoScope(Object bean) {
        if (bean.getClass().isAnnotationPresent(MicroserviceNamespace.class)) {
            JarDetail detail = new JarDetail(bean.getClass());
            if (detail != null && detail.getName() != null) {
                setScope(bean, detail.getName());
            }
        }
    }

    public static void setScope(Object bean, String namespace) {
        MicroserviceNamespace beanMicroNamespace = bean.getClass().getAnnotation(MicroserviceNamespace.class);
        AnnotationUtils.changeAnnotationValue(beanMicroNamespace, "value", namespace);
    }

}
