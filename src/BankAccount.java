public class BankAccount {
    private String accountNumber;
    private double balance;

    private AccountTypeStrategy accountTypeStrategy;

    public BankAccount(String accountNumber, double balance, AccountTypeStrategy accountTypeStrategy){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountTypeStrategy = accountTypeStrategy;
    }

    public boolean hasSufficientBalance(int amount){
       return accountTypeStrategy.hasSufficientFunds(balance, amount);
    }

    public void withdraw(int amount){
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
