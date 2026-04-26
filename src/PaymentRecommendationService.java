import java.util.List;

// Strategy → handles LOB-specific recommendation behavior
// Factory → selects the right strategy
public class PaymentRecommendationService {
    private RecommendationStrategyFactory strategyFactory;

    public PaymentRecommendationService(RecommendationStrategyFactory strategyFactory){
        this.strategyFactory = strategyFactory;
    }

    public List<PaymentInstrument> recommend(User user, Cart cart, UserContext userContext){
        // decide which strategy to use based on line of business
        // Phase 1 - cart has only 1 lob: use cart.getLineOfBusiness()
        // phase 2 - cart has multiple lobs: use cart().getLineOfBusinesses()
        RecommendationStrategy strategy = strategyFactory.getStrategy(cart.getLineOfBusiness());

        return strategy.recommend(user,cart,userContext);
    }



}
