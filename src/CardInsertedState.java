public class CardInsertedState implements ATMState{

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException("Card already inserted");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        if(atm.getCurrentCard().validatePin(pin)){
            atm.setCurrentState(new AuthenticatedState());
            System.out.println("PIN Validated");
        }
        else{
            throw new IllegalStateException("Invalid PIN");
        }
    }

    @Override
    public void selectTransaction(ATM atm, TransactionType transactionType, int amount) {
        throw new IllegalStateException("Please enter PIN first");
    }

    @Override
    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setCurrentState(new NoCardState());
        System.out.println("Card ejected");
    }



}
