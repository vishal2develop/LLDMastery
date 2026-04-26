public interface PaymentRule {

    boolean isApplicable(
            PaymentInstrument instrument,
            Cart cart,
            User user,
            UserContext context
    );
}
