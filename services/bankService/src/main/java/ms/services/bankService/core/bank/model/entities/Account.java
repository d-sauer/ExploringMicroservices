package ms.services.bankService.core.bank.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by davor on 25/05/15.
 */
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"iban"}))
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String iban;

    private BigDecimal balance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
