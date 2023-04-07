package nl.maikel.mu_bank.transaction.repository;

import nl.maikel.mu_bank.transaction.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query(value = "SELECT id, account_id, amount, created_on, transaction_type, updated_balance, MAX(created_on) FROM transaction GROUP BY id, account_id, amount, created_on, transaction_type, updated_balance", nativeQuery = true)
    Transaction findTransactionByCreatedOnMax();
}
