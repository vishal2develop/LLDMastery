public class PaymentInstrument {
    private String id;
    private PaymentInstrumentType type;
    private String issuer;
    // relevanceScore determines which payment instrument to show first.
    private int relevanceScore;

    public PaymentInstrument(String id, PaymentInstrumentType type, String issuer, int relevanceScore){
        this.id = id;
        this.type = type;
        this.issuer = issuer;
        this.relevanceScore = relevanceScore;
    }

    public int getRelevanceScore() {
        return relevanceScore;
    }

    public PaymentInstrumentType getType() {
        return type;
    }

    public String getIssuer() {
        return issuer;
    }

    @Override
    public String toString() {
        return "PaymentInstrument{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", issuer='" + issuer + '\'' +
                ", relevanceScore=" + relevanceScore +
                '}';
    }
}
