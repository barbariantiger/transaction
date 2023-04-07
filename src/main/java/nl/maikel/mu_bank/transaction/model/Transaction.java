package nl.maikel.mu_bank.transaction.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import nl.maikel.mu_bank.transaction.event.TransactionType;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@RequiredArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    private final String accountId;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final BigDecimal updatedBalance;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;
}
