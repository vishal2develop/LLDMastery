public class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("UPI payment successful");
    }
}
