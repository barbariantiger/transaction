package nl.maikel.mu_bank.transaction.function;

import nl.maikel.mu_bank.transaction.event.TransactionEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class TransactionFunction {

    @Bean
    public Function<TransactionEvent, String> createTransaction() {
        return event -> "Hello World!";
    }
}
