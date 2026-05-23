public class ATM {
    private ATMState currentState;
    private Card currentCard;
    private CashDispenser cashDispenser;



    public ATM(){
        currentState = new NoCardState();

        // set up cash dispenser
        CashDispenser twoThousandDispenser = new TwoThousandDispenser();
        CashDispenser fiveHundredDispenser = new FiveHundredDispenser();
        CashDispenser hundredDispenser = new HundredDispenser();
        twoThousandDispenser.setNextCashDispenser(fiveHundredDispenser);
        fiveHundredDispenser.setNextCashDispenser(hundredDispenser);

        this.cashDispenser = twoThousandDispenser;
    }

    public void insertCard(Card card){
        currentState.insertCard(this, card);
    }

    public void enterPin(int pin){
        currentState.enterPin(this, pin);
    }
    public void dispenseCash(int amount){
        if(amount%100!=0){
            throw new IllegalArgumentException("Amount must be a multiple of 100");
        }
        currentState.dispenseCash(this, amount);
    }
    public void ejectCard(){
        currentState.ejectCard(this);
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public ATMState getCurrentState() {
        return currentState;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    CashDispenser getCashDispenser() {
        return cashDispenser;
    }
}
