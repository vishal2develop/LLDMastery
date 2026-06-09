public class Payment {
    private double amount;
    private PaymentStrategy paymentStrategy;


    public Payment(PaymentStrategy strategy, double amount) {
        this.paymentStrategy = strategy;
        this.amount = amount;
    }

    public PaymentStrategy getPaymentStrategy() { return paymentStrategy; }

    public double getAmount() { return amount; }

}
