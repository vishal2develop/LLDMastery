import java.util.Map;

public class TransactionLimitConfig {
    // Maintain a map of limits for each line of business and payment instrument
    private final Map<LineOfBusiness, Map<PaymentInstrumentType, Double>> limits;

    public TransactionLimitConfig(Map<LineOfBusiness, Map<PaymentInstrumentType, Double>> limits){
        this.limits = limits;
    }

    public double getLimit(LineOfBusiness lineOfBusiness, PaymentInstrumentType instrumentType){
        return limits
                .getOrDefault(lineOfBusiness,Map.of())
                .getOrDefault(instrumentType,Double.MAX_VALUE);
    }


}
