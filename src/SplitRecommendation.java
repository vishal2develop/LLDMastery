import java.util.List;

public class SplitRecommendation {
    private CartSplit cartSplit;
    private List<PaymentInstrument> paymentInstruments;

    public SplitRecommendation(CartSplit cartSplit, List<PaymentInstrument> paymentInstruments){
        this.cartSplit = cartSplit;
        this.paymentInstruments = paymentInstruments;
    }

    @Override
    public String toString() {
        return "LOB=" + cartSplit.getLineOfBusiness() +
                " | Amount=" + cartSplit.getTotalAmount() +
                " | Recommended=" + paymentInstruments;
    }
}
