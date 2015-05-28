package ms.services.bankService.core.bank.services;

import ms.services.bankService.core.bank.model.entities.Account;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public interface AccountService {

    List<Account> findAll();
}
