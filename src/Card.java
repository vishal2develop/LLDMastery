public class Card {
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private int pin;
    private BankAccount bankAccount;

    public Card(String cardNumber, String cardHolder, String expirationDate, int pin, BankAccount bankAccount){
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.pin = pin;
        this.bankAccount = bankAccount;
    }

    public boolean validatePin(int enteredPin){
        return pin == enteredPin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
