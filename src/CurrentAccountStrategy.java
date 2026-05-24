public class CurrentAccountStrategy implements AccountTypeStrategy{
    private static final double OVERDRAFT_LIMIT = 5000;
    @Override
    public boolean hasSufficientFunds(double balance, int amount) {
        // Overdraft is a feature that allows you to withdraw money from your account even if you don't have enough balance.'
        // Overdraft limit is 5000
        return (balance+OVERDRAFT_LIMIT) >= amount;
    }
    @Override
    public String getAccountType() {
        return "Current";
    }
}
