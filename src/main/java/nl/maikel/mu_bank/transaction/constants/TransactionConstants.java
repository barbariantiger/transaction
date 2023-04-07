package nl.maikel.mu_bank.transaction.constants;

public class TransactionConstants {

    private TransactionConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final String UTILITY_CLASS = "Utility class";
    public static final String GET_LAST_TRANSACTION = "SELECT id, account_id, amount, created_on, transaction_type, updated_balance, MAX(created_on) FROM transaction GROUP BY id, account_id, amount, created_on, transaction_type, updated_balance";
    public static final String TRANSACTION_TABLE = "TRANSACTION";
    public static final String MAPPER_COMPONENT_MODEL = "spring";
}
