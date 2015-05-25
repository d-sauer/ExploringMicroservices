package ms.api.service;

import java.lang.annotation.*;

/**
 * Created by davor on 24/04/15.
 * @see ms.api.service.util.MicroserviceNamespaceUtils
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MicroserviceNamespace {

    String value() default "";

}
