import java.time.Duration;

public class RateLimitConfig {
    // max requests per window
    private int maxRequests;
    private Duration window;

    public RateLimitConfig(int maxRequests, Duration window) {
        this.maxRequests = maxRequests;
        this.window = window;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public Duration getWindow() {
        return window;
    }
}
