package ro.microservice.accounting.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.microservice.accounting.models.AccountModel;
import ro.microservice.accounting.services.AccountService;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{cardId}")
    public Collection<AccountModel> getByCardId(@PathVariable("cardId") final Long cardId) {
        return accountService.getByCardId(cardId);
    }
}
