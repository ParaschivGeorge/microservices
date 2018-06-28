package ro.microservice.accounting.services;

import org.springframework.stereotype.Service;
import ro.microservice.accounting.mappers.AccountMapper;
import ro.microservice.accounting.models.AccountModel;
import ro.microservice.accounting.repositories.AccountRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Collection<AccountModel> getByCardId(Long cardId) {
        return accountRepository.findByCardId(cardId).stream()
                .map(AccountMapper::toModel)
                .collect(Collectors.toList());
    }
}
