public interface ATMState {
    void insertCard(ATM atm, Card card);
    void enterPin(ATM atm, int pin);
    void selectTransaction(ATM atm, TransactionType transactionType, int amount);
    void ejectCard(ATM atm);
}
