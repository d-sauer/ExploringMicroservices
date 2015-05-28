package ms.services.bankService.rest.resources.asm;

import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.rest.mvc.AccountController;
import ms.services.bankService.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by davor on 28/05/15.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> implements Logger {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();

        try {
            ObjectUtils.mapValues(account, resource);
        } catch (Exception e) {
            error("Can't map value", e);
        }

        // Add HATEOAS links
        // ----

        return resource;
    }
}
