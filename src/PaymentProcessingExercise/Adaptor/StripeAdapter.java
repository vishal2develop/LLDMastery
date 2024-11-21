package PaymentProcessingExercise.Adaptor;

import PaymentProcessingExercise.Adaptee.Stripe;
import PaymentProcessingExercise.TargetInterfaces.PaymentProcessor;

public class StripeAdapter implements PaymentProcessor {

    Stripe stripe;

    public StripeAdapter(Stripe stripe){
        this.stripe = stripe;
    }

    @Override
    public void pay(double amount) {
        stripe.processPayment(amount); // Delegates the payment to Stripe’s method
    }
}
