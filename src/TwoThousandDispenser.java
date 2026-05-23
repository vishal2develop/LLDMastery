public class TwoThousandDispenser extends CashDispenser{
    @Override
    public void dispenseCash(int amount) {
        int notes = (int) (amount / 2000);
        int remainingAmount = (int) (amount % 2000);
        if(notes > 0){
            System.out.println("Dispensing " + notes + " x 2000 notes");
        }

        if(remainingAmount > 0 && nextCashDispenser != null){
            nextCashDispenser.dispenseCash(remainingAmount);
        }
    }
}
