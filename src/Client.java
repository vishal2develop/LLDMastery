public class Client {
    public static void main(String[] args) throws Exception {
        BankAccount account = new BankAccount("123456789", 10000);
        Card card = new Card("123456789", "John Doe", "12/2025", 1234, account);

        ATM atm = new ATM();

        try{
            atm.insertCard(card);
            atm.enterPin(1234);
            atm.dispenseCash(2800);
            System.out.println("Remaining balance: " + account.getBalance());
        }
        catch(Exception e){
            System.out.println("Transaction failed: " + e.getMessage());
        }

    }
}
