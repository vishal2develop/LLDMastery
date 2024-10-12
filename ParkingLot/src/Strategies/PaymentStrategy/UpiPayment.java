package Strategies.PaymentStrategy;

import Interfaces.PaymentStrategy;

public class UpiPayment implements PaymentStrategy {
    @Override
    public void collectPayment(double amount) {
        System.out.println("Collected Payment in UPI Mode: "+amount);
    }
}
