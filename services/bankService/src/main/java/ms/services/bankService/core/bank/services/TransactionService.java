package ms.services.bankService.core.bank.services;

import ms.services.bankService.core.bank.model.entities.Transaction;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public interface TransactionService {

    List<Transaction> findAll();
}
