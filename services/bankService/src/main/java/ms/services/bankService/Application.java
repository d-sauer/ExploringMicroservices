package ms.services.bankService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
public class Application extends SpringApplication{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}