import java.util.List;

public class User {
    private String id;
    private List<PaymentInstrument> paymentInstruments;

    public User(String id, List<PaymentInstrument> paymentInstruments){
        this.id = id;
        this.paymentInstruments = paymentInstruments;
    }

    public List<PaymentInstrument> getPaymentInstruments() {
        return paymentInstruments;
    }
}
