public class HasCardState extends ATMState{

    public HasCardState(){
        System.out.println("enter your card pin number");
    }

    @Override
    public void authenticatePin(ATM atm, Card card, int pin) {
        System.out.println("PIN Authenticated");
        atm.setCurrentATMState(new SelectOperationState());
    }

    @Override
    public void exit(ATM atm) {
        returnCard();
        atm.setCurrentATMState(new IdleState());
        System.out.println("Exiting!!");
    }

    @Override
    public void returnCard() {
        System.out.println("Please collect your card");
    }
}
