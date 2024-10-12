package Services;

import Enums.PaymentType;
import Strategies.PaymentStrategy.CashPayment;
import Strategies.PaymentStrategy.UpiPayment;

public class PaymentService {
    public void processPayment(PaymentType paymentType,double amount){
        switch (paymentType){
            case UPI:
                System.out.println("Processing UPI Payment");
                UpiPayment upiPayment = new UpiPayment();
                upiPayment.collectPayment(amount);
                break;

            case CASH:
                System.out.println("Processing CASH Payment");
                CashPayment cashPayment = new CashPayment();
                cashPayment.collectPayment(amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid payment type");

        }

    }
}
