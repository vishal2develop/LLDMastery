import java.util.Map;


public class RecommendationStrategyFactory {
    private final Map<LineOfBusiness,RecommendationStrategy> strategies;

    public RecommendationStrategyFactory(Map<LineOfBusiness,RecommendationStrategy> strategies){
        this.strategies = strategies;
    }


    public RecommendationStrategy getStrategy(LineOfBusiness lineOfBusiness){
        // Instead of using new repeatedly inside the method, we can use a Map of lob->strategy and wire it at client level
        // return switch (lineOfBusiness){
        //     case COMMERCE -> new CommerceRecommendationStrategy();
        //     case INVESTMENT -> new InvestmentRecommendationStrategy();
        //     case CREDIT_CARD_BILL_PAYMENT -> new CreditCardBillRecommendationStrategy();
        // };

        // Cleaner approach
        RecommendationStrategy strategy = strategies.get(lineOfBusiness);
        if (strategy == null){
            throw new IllegalArgumentException("Unsupported line of business: " + lineOfBusiness);
        }
        return strategy;
    }
}
