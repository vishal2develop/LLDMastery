package PaymentProcessingExercise.Adaptee;

public class PayPal {
    public void makePayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}
