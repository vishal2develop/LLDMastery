import java.util.List;
import java.util.Map;

public class Client {

    public static void main(String[] args) {

        // Products
        Product headphones = new Product(
                "P1",
                "Headphones",
                5000.0,
                LineOfBusiness.COMMERCE
        );

        Product creditCardBill = new Product(
                "P2",
                "HDFC Credit Card Bill",
                10000.0,
                LineOfBusiness.CREDIT_CARD_BILL_PAYMENT
        );


        // Cart for commerce
        CartItem item1 = new CartItem("CI1", headphones, 2);
        Cart cart = new Cart("C1", List.of(item1));

        // Cart for credit card bill payment
        CartItem billItem = new CartItem("CI2", creditCardBill, 1);
        Cart billPaymentCart = new Cart("C2", List.of(billItem));


        // Payment instruments
        PaymentInstrument upi = new PaymentInstrument(
                "PI1",
                PaymentInstrumentType.UPI,
                "GooglePay",
                90
        );

        PaymentInstrument creditCard = new PaymentInstrument(
                "PI2",
                PaymentInstrumentType.CREDIT_CARD,
                "HDFC",
                80
        );

        PaymentInstrument netBanking = new PaymentInstrument(
                "PI3",
                PaymentInstrumentType.NET_BANKING,
                "ICICI",
                70
        );

        // User
        User user = new User(
                "U1",
                List.of(upi, creditCard, netBanking)
        );

        // Runtime context
        UserContext context = new UserContext(
                true,   // upiEnabled
                true    // netBankingEnabled
        );

        // Define transaction limits for each line of business and payment instrument type
        TransactionLimitConfig limitConfig = new TransactionLimitConfig(
                Map.of(
                        LineOfBusiness.COMMERCE, Map.of(
                                PaymentInstrumentType.UPI, 100000.0,
                                PaymentInstrumentType.DEBIT_CARD, 200000.0,
                                PaymentInstrumentType.CREDIT_CARD, 500000.0
                        ),
                        LineOfBusiness.INVESTMENT, Map.of(
                                PaymentInstrumentType.UPI, 200000.0,
                                PaymentInstrumentType.NET_BANKING, 1000000.0,
                                PaymentInstrumentType.DEBIT_CARD, 300000.0
                        )
                )
        );

        // Define the rules
        RecommendationStrategyFactory strategyFactory = getRecommendationStrategyFactory(limitConfig);

        // Create the Payment Recommendation service
        PaymentRecommendationService recommendationService = new PaymentRecommendationService(strategyFactory);


        // List<PaymentInstrument> recommendedInstruments = recommendationService.recommend(user, billPaymentCart, context); // for credit card bill payment testing
        List<PaymentInstrument> recommendedInstruments = recommendationService.recommend(user, cart, context); // for commerce testing & Investment testing

        recommendedInstruments.forEach(System.out::println);
    }

    private static RecommendationStrategyFactory getRecommendationStrategyFactory(TransactionLimitConfig limitConfig) {
        List<PaymentRule> commonRules = List.of(
                new TransactionLimitRule(limitConfig),
                new UpiCapabilityRule(),
                new NetBankingCapabilityRule()
        );

        // Create the different strategies
        RecommendationStrategy commerceStrategy = new CommerceRecommendationStrategy(commonRules);
        RecommendationStrategy investmentStrategy = new InvestmentRecommendationStrategy(commonRules);
        RecommendationStrategy creditCardBillStrategy = new CreditCardBillRecommendationStrategy(List.of(
                new CreditCardBillRestrictionRule(),
                new TransactionLimitRule(limitConfig),
                new UpiCapabilityRule(),
                new NetBankingCapabilityRule()
        ));

        // Create the strategy factory
        RecommendationStrategyFactory strategyFactory = new RecommendationStrategyFactory(Map.of(
                LineOfBusiness.COMMERCE, commerceStrategy,
                LineOfBusiness.INVESTMENT, investmentStrategy,
                LineOfBusiness.CREDIT_CARD_BILL_PAYMENT, creditCardBillStrategy
        ));
        return strategyFactory;
    }
}
