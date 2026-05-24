import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        // Savings account
        BankAccount savingsAccount = new BankAccount("123456789", 10000,new SavingsAccountStrategy());
        Card savingsAccountCard = new Card("123456789", "John Doe", "12/2025", 1234, savingsAccount);

        // Current account
        BankAccount currentAccount = new BankAccount("555555555", 10000,new CurrentAccountStrategy());
        Card currentAccountCard = new Card("5555-5555-5555", "Jane Doe", "12/2025", 5555, currentAccount);

        ATM atm = new ATM();

        try{
            // Withdraw 1000 from savings account
            System.out.println("Savings account Testing");
            atm.insertCard(savingsAccountCard);
            atm.enterPin(1234);
            atm.selectTransaction(TransactionType.WITHDRAW, 1000);
            System.out.println("Remaining balance: " + savingsAccount.getBalance());
        }
        catch(Exception e){
            System.out.println("Transaction failed: " + e.getMessage());
        }

        System.out.println();
        try{
            // Withdraw 15000 from current account - Overdraft
            System.out.println("Current account Testing");
            atm.insertCard(currentAccountCard);
            atm.enterPin(5555);
            atm.selectTransaction(TransactionType.WITHDRAW, 15000);
            System.out.println("Remaining balance: " + currentAccount.getBalance());
        }
        catch(Exception e){
            System.out.println("Transaction failed: " + e.getMessage());
        }

    }
}
