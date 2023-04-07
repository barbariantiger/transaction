package nl.maikel.mu_bank.transaction;

import nl.maikel.mu_bank.transaction.mapper.TransactionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nl.maikel.mu_bank.transaction.MockedEntities.*;
import static nl.maikel.mu_bank.transaction.TestConstants.CREATED_ON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionApplicationTests {

	@Autowired
	private TransactionMapper transactionMapper;

	@Test
	void givenFistTransactionWhenTransactionEventToTransactionThenTransactionEvent() {
		var transaction = this.transactionMapper.transactionEventToTransaction(getTransactionEvent(), null);
		assertThat(transaction).usingRecursiveComparison().ignoringFields(CREATED_ON).isEqualTo(getFirstTransaction());
	}

	@Test
	void givenSecondTransactionWhenTransactionEventToTransactionThenTransactionEvent() {
		var transaction = this.transactionMapper.transactionEventToTransaction(getTransactionEvent(), getFirstTransaction());
		assertThat(transaction).usingRecursiveComparison().ignoringFields(CREATED_ON).isEqualTo(getSecondTransaction());
	}

	@Test
	void givenFistTransactionWhenTransactionToTransactionProcessedEventThenTransactionProcessedEvent() {
		var transaction = this.transactionMapper.transactionToTransactionProcessedEvent(getFirstTransaction());
		assertThat(transaction).usingRecursiveComparison().ignoringFields(CREATED_ON).isEqualTo(getFirstTransactionProcessedEvent());
	}

	@Test
	void givenSecondTransactionWhenTransactionToTransactionProcessedEventThenTransactionProcessedEvent() {
		var transaction = this.transactionMapper.transactionToTransactionProcessedEvent(getSecondTransaction());
		assertThat(transaction).usingRecursiveComparison().ignoringFields(CREATED_ON).isEqualTo(getSecondTransactionProcessedEvent());
	}
}
