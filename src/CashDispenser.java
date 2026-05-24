public abstract class CashDispenser {
    protected CashDispenser nextCashDispenser;

    public void setNextCashDispenser(CashDispenser cashDispenser){
        this.nextCashDispenser = cashDispenser;
    }

    public synchronized void dispenseCash(int amount){
        dispense(amount);
    }

    protected abstract void dispense(int amount);
}
