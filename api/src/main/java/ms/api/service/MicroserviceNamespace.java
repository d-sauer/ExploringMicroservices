package ms.api.service;

import java.lang.annotation.*;

/**
 * Created by davor on 24/04/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MicroserviceNamespace {

    String value() default "";

}
