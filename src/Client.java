import java.time.Duration;

public class Client {
    public static void main(String[] args) throws Exception {

        RateLimitConfig config =
                new RateLimitConfig(3, Duration.ofSeconds(10));

        RateLimitingStrategy strategy =
                new FixedWindowStrategy(config);

        RateLimiter rateLimiter =
                new RateLimiter(strategy);

        testSameClient(rateLimiter);

        System.out.println();

        testDifferentClients(rateLimiter);

        testWindowReset(rateLimiter);
    }

    private static void testSameClient(RateLimiter rateLimiter) {
        System.out.println("Same client test:");

        String clientId = "client-1";

        for (int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiter.allowRequest(clientId);

            System.out.println(
                    "Request " + i + " -> " +
                            (allowed ? "ALLOWED" : "REJECTED")
            );
        }
    }

    private static void testDifferentClients(
            RateLimiter rateLimiter
    ) {
        System.out.println("Different clients test:");

        String[] clients = {
                "client-4",
                "client-5",
                "client-6"
        };

        for (String clientId : clients) {
            boolean allowed =
                    rateLimiter.allowRequest(clientId);

            System.out.println(
                    clientId + " -> " +
                            (allowed ? "ALLOWED" : "REJECTED")
            );
        }
    }

    // Test the window reset after 10 seconds
    private static void testWindowReset(
            RateLimiter rateLimiter
    ) throws Exception {

        System.out.println("Window reset test:");

        String clientId = "client-reset";

        for (int i = 1; i <= 3; i++) {
            rateLimiter.allowRequest(clientId);
        }

        // simulate a delay of 10 seconds
        Thread.sleep(11000);

        // allow the client to make a request again with a fresh window.
        System.out.println(
                rateLimiter.allowRequest(clientId)
        );
    }


}