package ms.services.userService;

import ms.api.service.EnableCxp;
import ms.services.userService.core.model.entities.Address;
import ms.services.userService.core.model.entities.User;
import ms.services.userService.core.repositories.AddressRepository;
import ms.services.userService.core.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class })
@EnableCxp
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * e.g gradle bootRun -Pargs="arg1 arg2"
     * @param args
     */
    public static void main(String [] args) {
        log.debug("userService service");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.trace("Console runner, args: {}", Arrays.asList(args));

        Arrays.asList(args).forEach(arg -> {
            if (arg.equalsIgnoreCase("fillDB")) {
                fillDb();
            }
        });
    }

    private void fillDb() {
        User user1 = new User("davor", "pass", "Davor", "Sauer'");
        User user2 = new User("anita", "pass", "Anita", "Bendelja'");
        userRepository.save(user1);
        userRepository.save(user2);

        Address address1 = new Address("Venetiehof 159, 1019ND, Amsterdam");
        Address address2 = new Address("Maderasevec 8, 42525 Maritjanec, Croatia");
        Address address3 = new Address("Velimirovac 81, 31500 Nasice, Croatia");

        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        Set<Address> davorAddresses = new HashSet<>();
        davorAddresses.add(address1);
        davorAddresses.add(address3);

        user1.setAddresses(davorAddresses);
        userRepository.save(user1);

        Set<Address> anitaAddresses = new HashSet<>();
        anitaAddresses.add(address1);
        anitaAddresses.add(address2);

        user2.setAddresses(anitaAddresses);
        userRepository.save(user2);
    }
}