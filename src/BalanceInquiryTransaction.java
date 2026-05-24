public class BalanceInquiryTransaction extends ATMTransaction{
    @Override
    protected void validate(ATM atm) {
        if(atm.getCurrentCard() == null){
            throw new IllegalStateException("Please insert card first");
        }
    }

    @Override
    protected void execute(ATM atm) {
        double balance = atm.getCurrentCard().getBankAccount().getBalance();
        System.out.println("Current Balance: "+balance);
    }
}
