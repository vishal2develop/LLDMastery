public interface ATMState {
    void insertCard(ATM atm, Card card);
    void enterPin(ATM atm, int pin);
    void dispenseCash(ATM atm, int amount);
    void ejectCard(ATM atm);
}
