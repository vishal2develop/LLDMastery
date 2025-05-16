public class OneHundredWithdrawProcessor extends CashWithdrawProcessor{
    public OneHundredWithdrawProcessor(CashWithdrawProcessor cashWithdrawProcessor){
        super(cashWithdrawProcessor);
    }

    @Override
    public void withdraw(ATM atm, int amount) {
        int required = amount/100;
        int balance = amount%100;

        if(required<= atm.getNoOfOneHundredNotes()){
            atm.deductOneHundredNotes(required);
        } else if (required>atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(atm.getNoOfOneHundredNotes());
            balance = balance+ (required-atm.getNoOfOneHundredNotes())*100;
        }

        if(balance!=0){
            System.out.println("Something went wrong");
        }
    }
}
