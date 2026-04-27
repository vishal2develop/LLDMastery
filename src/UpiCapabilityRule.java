public class UpiCapabilityRule implements PaymentRule{
    @Override
    public boolean isApplicable(PaymentInstrument instrument, PayableContext payableContext, User user, UserContext context) {
        return true;
    }
}
