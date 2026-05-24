// Template Method Pattern
// Explanation: validate and execute are abstract methods and need to be implemented by subclasses (actual transactions)
public abstract class ATMTransaction {
    public final void process(ATM atm){
        validate(atm);
        execute(atm);
        printReceipt();
    }
    protected abstract void validate(ATM atm);
    protected abstract void execute(ATM atm);

    private void printReceipt(){
        System.out.println( "Receipt Printed");
    }
}
