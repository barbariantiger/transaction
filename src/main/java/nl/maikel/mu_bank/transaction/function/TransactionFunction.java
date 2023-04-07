package nl.maikel.mu_bank.transaction.function;

import nl.maikel.mu_bank.transaction.event.EventType;
import nl.maikel.mu_bank.transaction.event.TransactionEvent;
import nl.maikel.mu_bank.transaction.event.TransactionProcessedEvent;
import nl.maikel.mu_bank.transaction.event.TransactionType;
import nl.maikel.mu_bank.transaction.model.Transaction;
import nl.maikel.mu_bank.transaction.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.function.Function;

@Configuration
public class TransactionFunction {

    private final TransactionRepository transactionRepository;

    public TransactionFunction(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Bean
    public Function<TransactionEvent, TransactionProcessedEvent> createTransaction() {
        return event -> {
            Transaction transaction = this.transactionRepository.findTransactionByCreatedOnMax();
            return switch (event.getTransactionType()) {
                case DEPOSIT -> processDeposit(event, transaction);
            };
        };
    }
    private TransactionProcessedEvent processDeposit(TransactionEvent event, Transaction transaction) {
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(event.getAmount()).add(transaction.getUpdatedBalance());
        Transaction newTransaction = new Transaction(
                event.getAccountId(),
                event.getTransactionType(),
                event.getAmount(),
                sum);
        Transaction result = this.transactionRepository.save(newTransaction);
        return new TransactionProcessedEvent(EventType.TRANSACTION_PROCESSED,
                TransactionType.DEPOSIT, result.getAccountId(), result.getAmount(), result.getUpdatedBalance(), result.getCreatedOn());
    }
}
