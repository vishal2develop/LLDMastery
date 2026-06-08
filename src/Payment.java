public class Payment {
    private PaymentModes mode;
    private double amount;

    public Payment(PaymentModes mode, double amount) {
        this.mode = mode;
        this.amount = amount;
    }

    public PaymentModes getMode() { return mode; }

    public double getAmount() { return amount; }

}
