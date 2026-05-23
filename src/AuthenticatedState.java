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
    public void dispenseCash(ATM atm, int amount) {
        // get back account
        BankAccount bankAccount = atm.getCurrentCard().getBankAccount();
        // check if account has sufficient balance
        if(!bankAccount.hasSufficientBalance(amount)){
            throw new IllegalStateException("Insufficient balance");
        }

        // withdraw money from account
        bankAccount.withdraw(amount);
        // dispense cash
        atm.getCashDispenser().dispenseCash(amount);

        // eject card
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
