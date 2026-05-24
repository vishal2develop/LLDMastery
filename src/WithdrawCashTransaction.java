public class WithdrawCashTransaction extends ATMTransaction{
    private int amount;

    public WithdrawCashTransaction(int amount){
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    @Override
    public void validate(ATM atm) {
        BankAccount bankAccount = atm.getCurrentCard().getBankAccount();
        if(!bankAccount.hasSufficientBalance(amount)){
            throw new IllegalStateException("Insufficient balance");
        }
    }

    @Override
    public void execute(ATM atm) {
        BankAccount bankAccount = atm.getCurrentCard().getBankAccount();
        bankAccount.withdraw(amount);
        atm.getCashDispenser().dispenseCash(amount);
        System.out.println("Cash withdrawn: "+amount);
    }

}
