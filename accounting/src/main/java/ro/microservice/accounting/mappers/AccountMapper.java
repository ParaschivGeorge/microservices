package ro.microservice.accounting.mappers;

import ro.microservice.accounting.entities.Account;
import ro.microservice.accounting.models.AccountModel;

public class AccountMapper {

    public static AccountModel toModel(final Account account) {
        return AccountModel.builder()
                .cardId(account.getCardId())
                .name(account.getName())
                .build();
    }

}
