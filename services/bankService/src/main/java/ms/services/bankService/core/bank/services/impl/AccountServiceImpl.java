package ms.services.bankService.core.bank.services.impl;

import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.repositories.AccountRepository;
import ms.services.bankService.core.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }
}
