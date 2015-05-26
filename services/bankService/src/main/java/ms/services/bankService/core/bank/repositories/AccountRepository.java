package ms.services.bankService.core.bank.repositories;

import ms.services.bankService.core.bank.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by davor on 25/05/15.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
