package ms.services.calculator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { ms.services.calculator.rest.CalculatorController.class })
public class AppConfiguration {

}
