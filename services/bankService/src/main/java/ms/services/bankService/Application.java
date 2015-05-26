package ms.services.bankService;

import ms.api.service.EnableCxp;
import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
@EnableCxp
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

//    @Autowired
//    private AccountRepository accountRepository;

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
        Account account = new Account();
        account.setIban("445645645634");
        account.setBalance(new BigDecimal(10000));

//        accountRepository.save(account);
    }
}