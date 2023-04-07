package nl.maikel.mu_bank.transaction.repository;

import nl.maikel.mu_bank.transaction.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import static nl.maikel.mu_bank.transaction.constants.TransactionConstants.GET_LAST_TRANSACTION;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query(value = GET_LAST_TRANSACTION, nativeQuery = true)
    Transaction findTransactionByCreatedOnMax();
}
