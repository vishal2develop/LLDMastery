package PaymentProcessingExercise;

import PaymentProcessingExercise.Adaptee.PayPal;
import PaymentProcessingExercise.Adaptee.Stripe;
import PaymentProcessingExercise.Adaptor.PayPalAdapter;
import PaymentProcessingExercise.Adaptor.StripeAdapter;
import PaymentProcessingExercise.Client.PaymentSystem;
import PaymentProcessingExercise.TargetInterfaces.PaymentProcessor;

public class Main {
    public static void main(String[] args) {
        PaymentSystem paymentSystem = new PaymentSystem();

        // using paypal through its adaptor
        PaymentProcessor paypalAdaptor = new PayPalAdapter(new PayPal());
        paymentSystem.processPayment(paypalAdaptor,100.00);

        // Using Stripe through its adapter
        PaymentProcessor stripeAdapter = new StripeAdapter(new Stripe());
        paymentSystem.processPayment(stripeAdapter, 150.0);

    }
}
