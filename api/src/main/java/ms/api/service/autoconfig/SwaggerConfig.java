package ms.api.service.autoconfig;

import ms.commons.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

/**
 *
 * Swagger UI: http://localhost:8080/swagger-ui.html
 * Swagger REST: http://localhost:8080/v2/api-docs/
 *
 * https://github.com/springfox/springfox
 * https://github.com/swagger-api/swagger-ui
 *
 * Created by davor on 08/05/15.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements Logger {

    @PostConstruct
    public void init() {
        info("Enable CXP Swagger support");
    }

    @Bean
    public Docket getApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Backbase CXP",
                "Backbase CXP API description",
                "Verision 0.0.1",
                "www.backbase.com",
                "sales-eu@backbase.com",
                "Apache License 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }

}
