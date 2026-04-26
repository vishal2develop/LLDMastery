public class NetBankingCapabilityRule implements PaymentRule{
    @Override
    public boolean isApplicable(PaymentInstrument instrument, Cart cart, User user, UserContext context) {
        return true;
    }
}
