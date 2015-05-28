package ms.services.bankService.rest.mvc;

import ms.services.bankService.core.bank.model.entities.Account;
import ms.services.bankService.core.bank.services.AccountService;
import ms.services.bankService.rest.resources.AccountListResource;
import ms.services.bankService.rest.resources.asm.AccountListResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by davor on 27/05/15.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<AccountListResource> findAllAccounts() {
        List<Account> accounts = accountService.findAll();

        AccountListResource accountList = new AccountListResourceAsm().toResource(accounts);

        return new ResponseEntity<AccountListResource>(accountList, HttpStatus.OK);
    }
}
