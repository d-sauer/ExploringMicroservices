package ms.services.bankService.rest.resources;

import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.bankService.core.bank.model.entities.Account;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

/**
 * Created by davor on 28/05/15.
 */
public class AccountResource extends ResourceSupport implements Logger {

    private String iban;

    private BigDecimal balance;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account toAccount()  {
        Account account = new Account();
        try {
            ObjectUtils.mapValues(this, account);
        } catch (Exception e) {
            error("Can't map values", e);
        }

        return account;
    }
}
