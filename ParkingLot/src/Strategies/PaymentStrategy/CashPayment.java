package Strategies.PaymentStrategy;

import Interfaces.PaymentStrategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public void collectPayment(double amount) {
        System.out.println("Collected Payment in CASH Mode for " + amount);
    }
}
