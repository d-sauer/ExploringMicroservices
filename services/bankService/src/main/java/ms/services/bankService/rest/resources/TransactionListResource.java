package ms.services.bankService.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public class TransactionListResource extends ResourceSupport {

    private List<TransactionResource> transactions;

    public List<TransactionResource> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResource> transactions) {
        this.transactions = transactions;
    }
}
