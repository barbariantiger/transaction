package nl.maikel.mu_bank.transaction;

import nl.maikel.mu_bank.transaction.event.EventType;
import nl.maikel.mu_bank.transaction.event.TransactionEvent;
import nl.maikel.mu_bank.transaction.event.TransactionProcessedEvent;
import nl.maikel.mu_bank.transaction.event.TransactionType;
import nl.maikel.mu_bank.transaction.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static nl.maikel.mu_bank.transaction.TestConstants.TEST_ACCOUNT_ID;

public class MockedEntities {

    public static TransactionEvent getTransactionEvent() {
        return new TransactionEvent(EventType.CREATE_TRANSACTION, TransactionType.DEPOSIT, BigDecimal.ONE, TEST_ACCOUNT_ID);
    }

    public static Transaction getFirstTransaction() {
        var transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccountId(TEST_ACCOUNT_ID);
        transaction.setAmount(BigDecimal.ONE);
        transaction.setUpdatedBalance(BigDecimal.ONE);
        transaction.setCreatedOn(LocalDateTime.now());
        return transaction;
    }

    public static Transaction getSecondTransaction() {
        var transaction = getFirstTransaction();
        var newTransaction = new Transaction();
        newTransaction.setId(transaction.getId());
        newTransaction.setTransactionType(transaction.getTransactionType());
        newTransaction.setAccountId(transaction.getAccountId());
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setUpdatedBalance(transaction.getUpdatedBalance().add(newTransaction.getAmount()));
        newTransaction.setCreatedOn(transaction.getCreatedOn());
        return newTransaction;
    }

    public static TransactionProcessedEvent getSecondTransactionProcessedEvent() {
        var newTransaction = getSecondTransaction();
        var transactionProcessedEvent = new TransactionProcessedEvent();
        transactionProcessedEvent.setAccountId(newTransaction.getAccountId());
        transactionProcessedEvent.setTransactionId(newTransaction.getId());
        transactionProcessedEvent.setType(EventType.TRANSACTION_PROCESSED);
        transactionProcessedEvent.setTransactionType(newTransaction.getTransactionType());
        transactionProcessedEvent.setAmount(newTransaction.getAmount());
        transactionProcessedEvent.setUpdatedBalance(newTransaction.getUpdatedBalance());
        transactionProcessedEvent.setCreatedOn(newTransaction.getCreatedOn());
        return transactionProcessedEvent;
    }

    public static TransactionProcessedEvent getFirstTransactionProcessedEvent() {
        var lastTransaction = getFirstTransaction();
        var transactionProcessedEvent = new TransactionProcessedEvent();
        transactionProcessedEvent.setAccountId(lastTransaction.getAccountId());
        transactionProcessedEvent.setTransactionId(lastTransaction.getId());
        transactionProcessedEvent.setType(EventType.TRANSACTION_PROCESSED);
        transactionProcessedEvent.setTransactionType(lastTransaction.getTransactionType());
        transactionProcessedEvent.setAmount(lastTransaction.getAmount());
        transactionProcessedEvent.setUpdatedBalance(BigDecimal.ONE);
        transactionProcessedEvent.setCreatedOn(lastTransaction.getCreatedOn());
        return transactionProcessedEvent;
    }
}
