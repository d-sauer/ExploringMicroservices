package ms.services.bankService.core.bank.services.impl;

import ms.services.bankService.core.bank.model.entities.Transaction;
import ms.services.bankService.core.bank.repositories.TransactionRepository;
import ms.services.bankService.core.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
@Service
@Transactional("bankTransactionManager")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }
}
