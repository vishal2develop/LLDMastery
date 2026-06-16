import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterMetrics {
    private final AtomicInteger allowedRequests = new AtomicInteger();
    private final AtomicInteger rejectedRequests = new AtomicInteger();

    public void incrementAllowedRequests() {
        allowedRequests.incrementAndGet();
    }

    public void incrementRejectedRequests() {
        rejectedRequests.incrementAndGet();
    }

    // getters

    public int getAllowedRequests() {
        return allowedRequests.get();
    }

    public int getRejectedRequests() {
        return rejectedRequests.get();
    }

}
