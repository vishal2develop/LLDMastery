import java.util.List;

// Strategy → handles LOB-specific recommendation behavior
// Factory → selects the right strategy
public class PaymentRecommendationService {
    private final RecommendationStrategyFactory strategyFactory;
    private final CartSplitter cartSplitter;


    public PaymentRecommendationService(RecommendationStrategyFactory strategyFactory, CartSplitter cartSplitter){
        this.strategyFactory = strategyFactory;
        this.cartSplitter = cartSplitter;
    }

    // Phase 1 - single LOB cart
    public List<PaymentInstrument> recommend(User user, Cart cart, UserContext userContext){
        // decide which strategy to use based on line of business
        RecommendationStrategy strategy = strategyFactory.getStrategy(cart.getLineOfBusiness());

        return strategy.recommend(user,cart,userContext);
    }

    // phase 2 - mixed LOB cart
    public List<SplitRecommendation> recommendForMixedCart(User user, Cart cart, UserContext userContext){
        return cartSplitter.splitByLineOfBusiness(cart).stream()
                .map(split -> {
                    RecommendationStrategy strategy =
                            strategyFactory.getStrategy(split.getLineOfBusiness());

                    return new SplitRecommendation(
                            split,
                            strategy.recommend(user, split, userContext)
                    );
                })
                .toList();
    }
}
