public class NoCardState implements ATMState{
    @Override
    public void insertCard(ATM atm, Card card) {
        atm.setCurrentCard(card);
        atm.setCurrentState(new CardInsertedState());
        System.out.println("Card inserted");
    }
    @Override
    public void enterPin(ATM atm, int pin) {
        throw new IllegalStateException("Please insert card first");
    }
    @Override
    public void dispenseCash(ATM atm, int amount) {
        throw new IllegalStateException("Please insert card first");
    }
    @Override
    public void ejectCard(ATM atm) {
        throw new IllegalStateException("No card inserted");
    }
}
