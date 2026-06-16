// Acts as the facade/orchestrator for the Rate limiting
public class RateLimiter {
    private final RateLimitingStrategy strategy;
    public RateLimiter(RateLimitingStrategy strategy) {
        this.strategy = strategy;
    }
    public boolean allowRequest(String clientId) {
        return strategy.allowRequest(clientId);
    }
}
