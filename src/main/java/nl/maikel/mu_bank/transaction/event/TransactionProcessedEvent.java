package nl.maikel.mu_bank.transaction.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionProcessedEvent {
    private EventType type;
    private TransactionType transactionType;
    private String accountId;
    private BigDecimal amount;
    private BigDecimal updatedBalance;
    private LocalDateTime createdOn;
}
