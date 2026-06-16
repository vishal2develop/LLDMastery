public class RateLimiterMetrics {
    private int allowedRequests;
    private int rejectedRequests;

    public RateLimiterMetrics() {
        this.allowedRequests = 0;
        this.rejectedRequests = 0;
    }

    public synchronized void incrementAllowedRequests() {
        allowedRequests++;
    }

    public synchronized void incrementRejectedRequests() {
        rejectedRequests++;
    }

    // getters

    public synchronized int getAllowedRequests() {
        return allowedRequests;
    }

    public synchronized int getRejectedRequests() {
        return rejectedRequests;
    }

}
