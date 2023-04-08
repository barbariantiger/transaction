package nl.maikel.mu_bank.transaction.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionEvent {
    private EventType type;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String accountId;
}
