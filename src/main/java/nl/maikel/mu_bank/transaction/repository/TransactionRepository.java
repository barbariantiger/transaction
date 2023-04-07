package nl.maikel.mu_bank.transaction.repository;

import nl.maikel.mu_bank.transaction.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
    Transaction findTransactionByCreatedOnMax();
}
