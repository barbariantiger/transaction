package nl.maikel.mu_bank.transaction.model;

import jakarta.persistence.*;
import lombok.*;
import nl.maikel.mu_bank.transaction.event.TransactionType;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static nl.maikel.mu_bank.transaction.constants.TransactionConstants.TRANSACTION_TABLE;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity(name = TRANSACTION_TABLE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    private String accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal updatedBalance;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;
}
