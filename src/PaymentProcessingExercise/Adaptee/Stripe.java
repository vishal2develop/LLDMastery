package PaymentProcessingExercise.Adaptee;

public class Stripe {
    public void processPayment(double amount) {
        System.out.println("Processing Stripe payment of $" + amount);
    }
}
