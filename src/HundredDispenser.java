public class HundredDispenser extends CashDispenser{
    @Override
    public void dispenseCash(int amount) {
        int notes = (int) amount / 100;
        int remainingAmount = (int) (amount % 100);
        if(notes > 0){
            System.out.println("Dispensing " + notes + " x 100 notes");
        }

        // Hundred dispenser is the last dispenser in the chain.
        // If amount is still remaining, ATM cannot dispense it. (eg: 50 is remaining-> cannot dispense)
        if (remainingAmount > 0) {
            throw new IllegalStateException(
                    "Amount cannot be dispensed"
            );
        }
    }
}
