public interface RateLimitingStrategy {
    boolean allowRequest(String clientId);
}
