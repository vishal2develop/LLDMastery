import java.util.List;



public abstract class BaseRecommendationStrategy implements RecommendationStrategy{
    protected List<PaymentRule> rules;

    public BaseRecommendationStrategy(List<PaymentRule> rules){
        this.rules = rules;
    }

    @Override
    public List<PaymentInstrument> recommend(User user, Cart cart, UserContext userContext) {
        return user.getPaymentInstruments().stream()
                .filter(instrument->isEligible(instrument,cart,user,userContext))
                .sorted((a,b)->b.getRelevanceScore())
                .toList();
    }

    private boolean isEligible(PaymentInstrument instrument, Cart cart,User user, UserContext userContext){
        return rules.stream()
                .allMatch(rule->rule.isApplicable(instrument,cart,user,userContext));
    }
}
