public class TransactionLimitRule implements PaymentRule{
    private TransactionLimitConfig config;

    public TransactionLimitRule(TransactionLimitConfig config){
        this.config = config;
    }


    @Override
    public boolean isApplicable(PaymentInstrument instrument, Cart cart, User user, UserContext context) {
        // check if the instrument is of the same type as the line of business
        double limit = config.getLimit(cart.getLineOfBusiness(),instrument.getType());
        // check if the total amount of the cart is less than or equal to the limit -> transaction is allowed
        return cart.getTotalAmount() <= limit;
    }
}
