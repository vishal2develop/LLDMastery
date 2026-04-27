public class CreditCardBillRestrictionRule implements PaymentRule{
    @Override
    public boolean isApplicable(PaymentInstrument instrument, PayableContext payableContext, User user, UserContext context) {
        // For Credit Card Bill LOB, Do not allow bill to be paid with a credit card
        if(payableContext.getLineOfBusiness() == LineOfBusiness.CREDIT_CARD_BILL_PAYMENT){
            return instrument.getType() != PaymentInstrumentType.CREDIT_CARD;
        }

        // For other LOBs, allow
        return true;
    }
}
