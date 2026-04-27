public interface PaymentRule {

    boolean isApplicable(
            PaymentInstrument instrument,
            PayableContext payableContext,
            User user,
            UserContext context
    );
}
