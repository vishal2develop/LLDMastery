public interface AccountTypeStrategy {
    boolean hasSufficientFunds(double balance, int amount);
    String getAccountType();
}
