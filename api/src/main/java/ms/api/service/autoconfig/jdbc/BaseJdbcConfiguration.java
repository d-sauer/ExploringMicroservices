package ms.api.service.autoconfig.jdbc;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

/**
 * Created by davor on 04/06/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableJpaRepositories
@Documented
@Inherited
@Import(BaseJdbcFactoryBean.class)
public @interface BaseJdbcConfiguration  {

    String value() default "";

    String prefix() default "datasource";

    String prefixForDefault() default "default.datasource";

    String persistanceUnitName() default "basePersistanceUnit";

}
