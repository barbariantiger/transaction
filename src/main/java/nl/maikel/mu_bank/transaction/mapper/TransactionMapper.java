package nl.maikel.mu_bank.transaction.mapper;

import nl.maikel.mu_bank.transaction.event.EventType;
import nl.maikel.mu_bank.transaction.event.TransactionEvent;
import nl.maikel.mu_bank.transaction.event.TransactionProcessedEvent;
import nl.maikel.mu_bank.transaction.event.TransactionType;
import nl.maikel.mu_bank.transaction.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static nl.maikel.mu_bank.transaction.constants.TransactionConstants.MAPPER_COMPONENT_MODEL;

@Mapper(componentModel = MAPPER_COMPONENT_MODEL)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    default Transaction transactionEventToTransaction(TransactionEvent transactionEvent, Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAccountId(transactionEvent.getAccountId());
        newTransaction.setTransactionType(transactionEvent.getTransactionType());
        BigDecimal sum = BigDecimal.ZERO;
        if (transaction != null && transaction.getUpdatedBalance() != null) {
            newTransaction.setId(transaction.getId());
            sum = sum.add(transactionEvent.getAmount()).add(transaction.getUpdatedBalance());
        } else {
            sum = sum.add(transactionEvent.getAmount());
        }
        newTransaction.setUpdatedBalance(sum);
        newTransaction.setAmount(transactionEvent.getAmount());
        newTransaction.setCreatedOn(LocalDateTime.now());
        return newTransaction;
    }
    default TransactionProcessedEvent transactionToTransactionProcessedEvent(Transaction transaction) {
        TransactionProcessedEvent transactionProcessedEvent = new TransactionProcessedEvent();
        transactionProcessedEvent.setTransactionId(transaction.getId());
        transactionProcessedEvent.setType(EventType.TRANSACTION_PROCESSED);
        transactionProcessedEvent.setTransactionType(TransactionType.DEPOSIT);
        transactionProcessedEvent.setAccountId(transaction.getAccountId());
        transactionProcessedEvent.setAmount(transaction.getAmount());
        transactionProcessedEvent.setUpdatedBalance(transaction.getUpdatedBalance());
        transactionProcessedEvent.setCreatedOn(transaction.getCreatedOn());
        return transactionProcessedEvent;
    }
}
