public class RequestCounter {
    private int count;
    private long windowStartTime;

    public RequestCounter() {
        this.count = 0;
        this.windowStartTime = System.currentTimeMillis();
    }
    public void increment() {
        count++;
    }

    public void reset() {
        count = 0;
        windowStartTime = System.currentTimeMillis();
    }

    // Getters and Setters
    public int getCount() {
        return count;
    }
    public long getWindowStartTime() {
        return windowStartTime;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setWindowStartTime(long windowStartTime) {
        this.windowStartTime = windowStartTime;
    }
}
