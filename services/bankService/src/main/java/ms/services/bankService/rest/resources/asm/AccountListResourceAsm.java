package ms.services.bankService.rest.resources.asm;

import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.rest.mvc.AccountController;
import ms.services.bankService.rest.resources.AccountListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<List<Account>, AccountListResource> {

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(List<Account> accounts) {
        AccountListResource resource = new AccountListResource();
        resource.setResources(new AccountResourceAsm().toResources(accounts));

        return resource;
    }
}
