import java.util.Comparator;
import java.util.List;



public abstract class BaseRecommendationStrategy implements RecommendationStrategy{
    protected List<PaymentRule> rules;

    public BaseRecommendationStrategy(List<PaymentRule> rules){
        this.rules = rules;
    }

    @Override
    public List<PaymentInstrument> recommend(User user, PayableContext payableContext, UserContext userContext) {
        return user.getPaymentInstruments().stream()
                .filter(instrument->isEligible(instrument,payableContext,user,userContext))
                .sorted(Comparator.comparingInt(PaymentInstrument::getRelevanceScore).reversed())
                .toList();
    }

    private boolean isEligible(PaymentInstrument instrument, PayableContext payableContext,User user, UserContext userContext){
        return rules.stream()
                .allMatch(rule->rule.isApplicable(instrument,payableContext,user,userContext));
    }
}
