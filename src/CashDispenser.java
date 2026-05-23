public abstract class CashDispenser {
    protected CashDispenser nextCashDispenser;

    public void setNextCashDispenser(CashDispenser cashDispenser){
        this.nextCashDispenser = cashDispenser;
    }

    public abstract void dispenseCash(int amount);
}
