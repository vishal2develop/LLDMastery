public abstract class CashWithdrawProcessor {
    CashWithdrawProcessor nextCashWithdrawalProcessor;

    CashWithdrawProcessor(CashWithdrawProcessor cashWithdrawProcessor){
        this.nextCashWithdrawalProcessor = cashWithdrawProcessor;
    }

    public void withdraw(ATM atm, int amount){
        if(nextCashWithdrawalProcessor !=null){
            nextCashWithdrawalProcessor.withdraw(atm,amount);
        }
    }
}
