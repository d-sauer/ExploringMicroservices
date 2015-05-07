package ms.api.service;

import ms.api.service.autoconfigure.CxpAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Created by davor on 07/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import(CxpAutoConfiguration.class)     // there is no need for META-INF/spring.factories
public @interface EnableCxp {


}
