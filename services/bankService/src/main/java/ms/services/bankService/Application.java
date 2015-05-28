package ms.services.bankService;

import ms.api.service.EnableCxp;
import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.audit.repositories.AuditRepository;
import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.model.entities.Transaction;
import ms.services.bankService.core.bank.repositories.AccountRepository;
import ms.services.bankService.core.bank.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

@SpringBootApplication
@EnableCxp
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AuditRepository auditRepository;


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
            if (arg.equalsIgnoreCase("filldb")) {
                fillDb();
            }
        });
    }

    private void fillDb() {
        log.info("Fill database....");

        Random rand = new Random();

        for(int accNo = 0; accNo <= 10; accNo++) {
            Account account = new Account();
            account.setIban(rand.nextInt(900000000) + 100000000 + "");
            account.setBalance(new BigDecimal(rand.nextInt(100000)));
            accountRepository.save(account);

            Audit audit = new Audit();
            audit.setStart(Calendar.getInstance());
            audit.setType("User transfer");
            audit.setData("accNo: " + accNo + ", transfer from: " + account.getIban());
            auditRepository.save(audit);

            for(int accTran = 0; accTran <= 5; accTran++) {
                Transaction transaction = new Transaction();
                transaction.setAccountId(account);
                transaction.setDescription("test " + accTran);
                transaction.setAmount(new BigDecimal(1000));
                transaction.setDatetime(Calendar.getInstance());

                transactionRepository.save(transaction);
            }

            audit.setEnd(Calendar.getInstance());
            auditRepository.save(audit);
        }
    }
}