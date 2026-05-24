public class SavingsAccountStrategy implements AccountTypeStrategy{
    private static final double MIN_BALANCE = 1000;


    @Override
    public boolean hasSufficientFunds(double balance, int amount) {
        return (balance-amount) >= MIN_BALANCE;
    }
    @Override
    public String getAccountType() {
        return "Savings";
    }

}
