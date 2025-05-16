public class TwoThousandWithdrawProcessor extends CashWithdrawProcessor{
    public TwoThousandWithdrawProcessor(CashWithdrawProcessor cashWithdrawProcessor){
        super(cashWithdrawProcessor);
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        int required = amount/2000;
        int balance = amount%2000;

        if(required<=atm.getNoOfTwoThousandNotes()){
            atm.deductTwoThousandNotes(required);
        } else if (required> atm.getNoOfTwoThousandNotes()) {
            atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
            balance = balance+(required-atm.getNoOfTwoThousandNotes())*2000;

        }

        if(balance!=0){
            super.withdraw(atm,balance);
        }

    }
}
