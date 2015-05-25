package ms.services.bankService;

import ms.api.service.EnableCxp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@EnableCxp
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * e.g gradle bootRun -Pargs="arg1 arg2"
     * @param args
     */
    public static void main(String [] args) {
        log.debug("bankService service");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.trace("Console runner, args: {}", Arrays.asList(args));

        Arrays.asList(args).forEach(arg -> {
            if (arg.equalsIgnoreCase("initDB")) {
                initDB();
            }
        });
    }

    private void initDB() {

    }
}