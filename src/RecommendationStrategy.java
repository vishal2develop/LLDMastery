import java.util.List;

public interface RecommendationStrategy {
    List<PaymentInstrument> recommend(User user, PayableContext payableContext, UserContext userContext);
}
