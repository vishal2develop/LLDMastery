import java.util.List;

public interface RecommendationStrategy {
    List<PaymentInstrument> recommend(User user, Cart cart, UserContext userContext);
}
