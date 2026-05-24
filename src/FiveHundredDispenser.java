public class FiveHundredDispenser extends CashDispenser{
    @Override
    protected void dispense(int amount) {
        int notes = (int) amount / 500;
        int remainingAmount = (int) (amount % 500);
        if(notes > 0){
            System.out.println("Dispensing " + notes + " x 500 notes");
        }
        if(remainingAmount > 0 && nextCashDispenser != null){
            nextCashDispenser.dispenseCash(remainingAmount);
        }
    }
}
