import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimitingStrategy{
    // ConcurrentHashMap because we want to have multiple threads accessing the map concurrently
    // clientId -> RequestCounter
    private final ConcurrentHashMap<String, RequestCounter> counters;
    private final RateLimitConfig config;

    public FixedWindowStrategy(RateLimitConfig config) {
        this.config = config;
        this.counters = new ConcurrentHashMap<>();
    }
    @Override
    public boolean allowRequest(String clientId) {
        // get the counter for the client, or create a new one if it doesn't exist'
        RequestCounter counter = counters.computeIfAbsent(clientId, k -> new RequestCounter());


        // if the counter is within the window, increment the counter and allow the request
        // check and increment is not thread safe.
        // For now, we can synchronize on the counter object.
        synchronized (counter) {
            // if current window expired, reset the counter
            long currentTime = System.currentTimeMillis();
            boolean windowExpired = currentTime>=
                    counter.getWindowStartTime()
                            +config.getWindow().toMillis();
            if (windowExpired) {
                counter.reset();
            }

            // if the counter is within the window, increment it and allow the request
            if (counter.getCount() < config.getMaxRequests()) {
                counter.increment();
                return true;
            }
            // else, the request is not allowed
            return false;
        }

    }
}
