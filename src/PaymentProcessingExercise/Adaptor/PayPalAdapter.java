package PaymentProcessingExercise.Adaptor;

import PaymentProcessingExercise.Adaptee.PayPal;
import PaymentProcessingExercise.TargetInterfaces.PaymentProcessor;

public class PayPalAdapter implements PaymentProcessor {

    private PayPal payPal;

    public PayPalAdapter(PayPal payPal){
        this.payPal=payPal;
    }

    @Override
    public void pay(double amount) {
        payPal.makePayment(amount); // Delegates the payment to PayPal’s method
    }
}
