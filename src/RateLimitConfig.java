import java.time.Duration;

public class RateLimitConfig {
    // max requests per window
    private int maxRequests;
    private Duration window;
    private RateLimiterType type;

    public RateLimitConfig(int maxRequests, Duration window, RateLimiterType type) {
        this.maxRequests = maxRequests;
        this.window = window;
        this.type = type;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public Duration getWindow() {
        return window;
    }

    public RateLimiterType getType() {
        return type;
    }
}
