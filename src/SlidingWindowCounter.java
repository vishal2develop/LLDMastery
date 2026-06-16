import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowCounter {

    // Stores timestamps of requests that are still
    // inside the active sliding window.
    //
    // Oldest timestamp is at the front.
    // Newest timestamp is at the back.
    private final Deque<Long> requestTimestamps =
            new ArrayDeque<>();

    public Deque<Long> getRequestTimestamps() {
        return requestTimestamps;
    }
}