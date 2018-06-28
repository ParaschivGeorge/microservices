package ro.microservice.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservice.accounting.entities.Account;

import java.util.Collection;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Collection<Account> findByCardId(final Long cardId);
}
