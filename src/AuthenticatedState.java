public class AuthenticatedState implements ATMState{

    @Override
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException("Card already inserted");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        throw new IllegalStateException("PIN already entered");
    }

    @Override
    public void selectTransaction(ATM atm, TransactionType transactionType, int amount) {
        ATMTransaction transaction = TransactionFactory.createTransaction(transactionType, amount);
        transaction.process(atm);

        // Post transaction processing -> eject card workflow
        atm.setCurrentCard(null);
        atm.setCurrentState(new NoCardState());
        System.out.println("Card ejected");
    }


    @Override
    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setCurrentState(new NoCardState());
        System.out.println("Card ejected");
    }



}
