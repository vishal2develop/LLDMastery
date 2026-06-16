import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowStrategy
        implements RateLimitingStrategy {

    // One sliding window counter per client.
    //
    // Example:
    //
    // clientA -> [1001,1005,1010]
    // clientB -> [1002,1008]
    //
    private final ConcurrentHashMap<
            String,
            SlidingWindowCounter
            > counters;

    // Contains:
    // maxRequests
    // windowDuration
    private final RateLimitConfig config;

    public SlidingWindowStrategy(
            RateLimitConfig config
    ) {
        this.config = config;
        this.counters = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String clientId) {

        // Get existing counter for client.
        //
        // Create one if client is seen for the
        // first time.
        SlidingWindowCounter counter =
                counters.computeIfAbsent(
                        clientId,
                        key -> new SlidingWindowCounter()
                );

        // Synchronize per client.
        //
        // Different clients can proceed concurrently.
        //
        // Same client's queue must be protected
        // because we'll modify timestamps.
        synchronized (counter) {

            long currentTime =
                    System.currentTimeMillis();

            // Beginning of active window.
            //
            // Example:
            // currentTime = 100
            // window = 60
            //
            // windowStart = 40
            //
            long windowStart =
                    currentTime
                            - config.getWindow().toMillis();

            Deque<Long> timestamps =
                    counter.getRequestTimestamps();

            // Remove expired requests.
            //
            // Any timestamp older than windowStart
            // is no longer relevant.
            //
            while (!timestamps.isEmpty()
                    && timestamps.peekFirst()
                    < windowStart) {

                timestamps.pollFirst();
            }

            // After cleanup,
            // queue size represents number of
            // requests inside current window.
            //
            if (timestamps.size()
                    < config.getMaxRequests()) {

                // Record current request.
                timestamps.addLast(currentTime);

                return true;
            }

            // Limit exceeded.
            return false;
        }
    }
}