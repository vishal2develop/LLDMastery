/**
 * BillingService orchestrates pricing
 * PricingStrategy calculates amount
 * Payment stores final amount
 */
public class BillingService {
    private PricingStrategy pricingStrategy;

    public BillingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Payment generatePayment(Reservation reservation) {

        try {
            double totalPrice = pricingStrategy.calculatePrice(reservation);
            System.out.println("Payment Generated");

            return new Payment(generatePaymentId(),reservation,totalPrice,PaymentStatus.PAID);
        }
        catch (Exception e){
            // System.out.println("Error in payment processing"+" "+e.getMessage());
            return new Payment(generatePaymentId(),reservation,0.0,PaymentStatus.FAILED);
        }

    }

    private String generatePaymentId() {
        return String.valueOf(System.nanoTime());
    }
}

