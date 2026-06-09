public class PaymentStrategyFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentModes paymentMode) {
        return switch (paymentMode){
            case CASH -> new CashPaymentStrategy();
            case CREDIT_CARD -> new CreditCardPaymentStrategy();
            case UPI -> new UpiPaymentStrategy();
        };
    }
}
