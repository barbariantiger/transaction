package nl.maikel.mu_bank.transaction;

import nl.maikel.mu_bank.transaction.mapper.TransactionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nl.maikel.mu_bank.transaction.MockedEntities.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionApplicationTests {

	@Autowired
	private TransactionMapper transactionMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void givenFistTransactionWhenTransactionEventToTransactionThenTransactionEvent() {
		var transaction = this.transactionMapper.transactionEventToTransaction(getTransactionEvent(), null);
		assertThat(transaction).usingRecursiveComparison().ignoringFields("createdOn").isEqualTo(getFirstTransaction());
	}

	@Test
	public void givenSecondTransactionWhenTransactionEventToTransactionThenTransactionEvent() {
		var transaction = this.transactionMapper.transactionEventToTransaction(getTransactionEvent(), getFirstTransaction());
		assertThat(transaction).usingRecursiveComparison().ignoringFields("createdOn").isEqualTo(getSecondTransaction());
	}

	@Test
	public void givenFistTransactionWhenTransactionToTransactionProcessedEventThenTransactionProcessedEvent() {
		var transaction = this.transactionMapper.transactionToTransactionProcessedEvent(getFirstTransaction());
		assertThat(transaction).usingRecursiveComparison().ignoringFields("createdOn").isEqualTo(getFirstTransactionProcessedEvent());
	}

	@Test
	public void givenSecondTransactionWhenTransactionToTransactionProcessedEventThenTransactionProcessedEvent() {
		var transaction = this.transactionMapper.transactionToTransactionProcessedEvent(getSecondTransaction());
		assertThat(transaction).usingRecursiveComparison().ignoringFields("createdOn").isEqualTo(getSecondTransactionProcessedEvent());
	}
}
