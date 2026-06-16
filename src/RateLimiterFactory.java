public class RateLimiterFactory {
    public static RateLimitingStrategy createStrategy(RateLimitConfig config){
        return switch (config.getType()){
            case FIXED_WINDOW -> new FixedWindowStrategy(config);
            case SLIDING_WINDOW -> new SlidingWindowStrategy(config);
        };
    }
}
