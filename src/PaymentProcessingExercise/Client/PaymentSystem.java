package PaymentProcessingExercise.Client;

import PaymentProcessingExercise.TargetInterfaces.PaymentProcessor;

public class PaymentSystem {

    public void processPayment(PaymentProcessor paymentProcessor, double amount){
        paymentProcessor.pay(amount); // Calls pay() on the provided adapter
    }

}
