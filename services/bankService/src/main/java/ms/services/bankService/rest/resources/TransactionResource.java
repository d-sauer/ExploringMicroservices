package ms.services.bankService.rest.resources;

import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.bankService.core.bank.model.entities.Transaction;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by davor on 28/05/15.
 */
public class TransactionResource extends ResourceSupport implements Logger {

    private String destination;

    private BigDecimal amount;

    private Calendar datetime;

    private String description;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Calendar getDatetime() {
        return datetime;
    }

    public void setDatetime(Calendar datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        try {
            ObjectUtils.mapValues(this, transaction);
        } catch (Exception e) {
            error("Can't map values", e);
        }
        return transaction;
    }
}
