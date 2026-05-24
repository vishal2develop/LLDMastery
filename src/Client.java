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
            atm.selectTransaction(TransactionType.WITHDRAW, 2800);
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

        System.out.println();
        testConcurrentWithdrawals();

    }

    private static void testConcurrentWithdrawals() throws Exception {
        System.out.println("Concurrent Withdrawal Testing");

        BankAccount account =
                new BankAccount("999999999", 10000, new SavingsAccountStrategy());

        Card card = new Card("999999999", "Concurrent User", "12/2025", 1234, account);

        ATM atm1 = new ATM();
        ATM atm2 = new ATM();

        Thread t1 = new Thread(() -> {
            try {
                atm1.insertCard(card);
                atm1.enterPin(1234);
                atm1.selectTransaction(TransactionType.WITHDRAW, 7000);
            } catch (Exception e) {
                System.out.println("ATM 1 failed: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                atm2.insertCard(card);
                atm2.enterPin(1234);
                atm2.selectTransaction(TransactionType.WITHDRAW, 7000);
            } catch (Exception e) {
                System.out.println("ATM 2 failed: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final balance: " + account.getBalance());
    }
}
