import java.time.Duration;

public class Client {


    public static void main(String[] args) throws Exception {

        RateLimiter fixedWindowLimiter =
                createFixedWindowRateLimiter();

        testSameClientLimit(fixedWindowLimiter);

        testDifferentClients(fixedWindowLimiter);

        testWindowReset(fixedWindowLimiter);

        RateLimiter slidingWindowLimiter =
                createSlidingWindowRateLimiter();

        testSlidingWindow(slidingWindowLimiter);
    }

    /**
     * Creates a Fixed Window rate limiter.
     *
     * Limit:
     * 3 requests every 10 seconds.
     */
    private static RateLimiter createFixedWindowRateLimiter() {

        RateLimitConfig config =
                new RateLimitConfig(
                        3,
                        Duration.ofSeconds(10)
                );

        return new RateLimiter(
                new FixedWindowStrategy(config)
        );
    }

    /**
     * Creates a Sliding Window rate limiter.
     *
     * Limit:
     * 3 requests every 10 seconds.
     */
    private static RateLimiter createSlidingWindowRateLimiter() {

        RateLimitConfig config =
                new RateLimitConfig(
                        3,
                        Duration.ofSeconds(10)
                );

        return new RateLimiter(
                new SlidingWindowStrategy(config)
        );
    }

    /**
     * Verifies that requests beyond the configured
     * limit are rejected for the same client.
     */
    private static void testSameClientLimit(
            RateLimiter rateLimiter
    ) {

        System.out.println(
                "\nSame Client Test:"
        );

        String clientId = "client-1";

        for (int i = 1; i <= 5; i++) {

            boolean allowed =
                    rateLimiter.allowRequest(clientId);

            System.out.println(
                    "Request " + i + " -> "
                            + (allowed
                            ? "ALLOWED"
                            : "REJECTED")
            );
        }
    }

    /**
     * Verifies that each client maintains
     * an independent request counter.
     */
    private static void testDifferentClients(
            RateLimiter rateLimiter
    ) {

        System.out.println(
                "\nDifferent Clients Test:"
        );

        String[] clients = {
                "client-2",
                "client-3",
                "client-4"
        };

        for (String clientId : clients) {

            boolean allowed =
                    rateLimiter.allowRequest(clientId);

            System.out.println(
                    clientId + " -> "
                            + (allowed
                            ? "ALLOWED"
                            : "REJECTED")
            );
        }
    }

    /**
     * Verifies that requests are allowed again
     * once the configured window expires.
     */
    private static void testWindowReset(
            RateLimiter rateLimiter
    ) throws InterruptedException {

        System.out.println(
                "\nWindow Reset Test:"
        );

        String clientId = "client-reset";

        for (int i = 0; i < 3; i++) {
            rateLimiter.allowRequest(clientId);
        }

        Thread.sleep(11000);

        System.out.println(
                "Request after reset -> "
                        + (rateLimiter.allowRequest(clientId)
                        ? "ALLOWED"
                        : "REJECTED")
        );
    }

    /**
     * Demonstrates Sliding Window behaviour.
     *
     * Requests are evaluated using a rolling
     * time window rather than fixed buckets.
     */
    private static void testSlidingWindow(
            RateLimiter rateLimiter
    ) {

        System.out.println(
                "\nSliding Window Test:"
        );

        String clientId = "sliding-client";

        for (int i = 1; i <= 5; i++) {

            boolean allowed =
                    rateLimiter.allowRequest(clientId);

            System.out.println(
                    "Request " + i + " -> "
                            + (allowed
                            ? "ALLOWED"
                            : "REJECTED")
            );
        }
    }

}
