package nl.maikel.mu_bank.transaction.event;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionProcessedEvent {
    private String transactionId;
    private EventType type;
    private TransactionType transactionType;
    private String accountId;
    private BigDecimal amount;
    private BigDecimal updatedBalance;
    private LocalDateTime createdOn;
}
