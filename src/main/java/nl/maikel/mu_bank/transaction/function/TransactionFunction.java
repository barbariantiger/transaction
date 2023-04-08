package nl.maikel.mu_bank.transaction.function;

import lombok.extern.slf4j.Slf4j;
import nl.maikel.mu_bank.transaction.event.TransactionEvent;
import nl.maikel.mu_bank.transaction.event.TransactionProcessedEvent;
import nl.maikel.mu_bank.transaction.mapper.TransactionMapper;
import nl.maikel.mu_bank.transaction.model.Transaction;
import nl.maikel.mu_bank.transaction.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static nl.maikel.mu_bank.transaction.constants.TransactionConstants.TRANSACTION_EVENT_RECEIVED;
import static nl.maikel.mu_bank.transaction.constants.TransactionConstants.TRANSACTION_PROCESSED;

@Slf4j
@Configuration
public class TransactionFunction {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionFunction(final TransactionRepository transactionRepository, final TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Bean
    public Function<TransactionEvent, TransactionProcessedEvent> createTransaction() {
        return event -> {
            Transaction transaction = this.transactionRepository.findTransactionByCreatedOnMax(event.getAccountId());
            return switch (event.getTransactionType()) {
                case DEPOSIT -> processDeposit(event, transaction);
            };
        };
    }
    private TransactionProcessedEvent processDeposit(TransactionEvent event, Transaction transaction) {
        log.debug(TRANSACTION_EVENT_RECEIVED, event);
        Transaction result = this.transactionRepository.save(transactionMapper.transactionEventToTransaction(event, transaction));
        log.debug(TRANSACTION_PROCESSED, result);
        return this.transactionMapper.transactionToTransactionProcessedEvent(result);
    }
}
