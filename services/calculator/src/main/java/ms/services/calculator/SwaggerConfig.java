package ms.services.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger UI: http://localhost:8080/swagger-ui.html
 * Swagger REST: http://localhost:8080/v2/api-docs/
 *
 * Created by davor on 08/05/15.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build();
    }

}
