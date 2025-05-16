public class Card {
    private int cardNumber;
    private int cvv;
    private int expiryDate;
    private String holderName;
    static int PIN_NUMBER = 112211;
    private UserBankAccount bankAccount;

    public boolean isCorrectPINEntered(int pin){
        return pin==PIN_NUMBER;
    }

    public double getBankBalance(){
        return bankAccount.balance;
    }

    public void deductBankBalance(double amount){
        bankAccount.withdrawalBalance(amount);
    }

    public void setBankAccount(UserBankAccount bankAccount){
        this.bankAccount = bankAccount;
    }



}
