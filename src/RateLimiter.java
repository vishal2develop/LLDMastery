// Acts as the facade/orchestrator for the Rate limiting
public class RateLimiter {
    private final RateLimitingStrategy strategy;
    private RateLimiterMetrics metrics;
    public RateLimiter(RateLimitingStrategy strategy) {
        this.strategy = strategy;
        this.metrics = new RateLimiterMetrics();
    }
    public boolean allowRequest(String clientId) {
        boolean allowed =  strategy.allowRequest(clientId);
        if (allowed) {
            metrics.incrementAllowedRequests();
        } else {
            metrics.incrementRejectedRequests();
        }
        return allowed;
    }

    public RateLimiterMetrics getMetrics() {
        return metrics;
    }
}
