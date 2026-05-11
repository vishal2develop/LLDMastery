// A road section whose traffic condition we want to monitor.
public class RoadSegment {
    private String segmentId;
    private String name;
    // Holds live traffic metrics
    private TrafficStats trafficStats;
    // holds the current congestion status
    private boolean congested;

    public RoadSegment(String segmentId, String name) {
        this.segmentId = segmentId;
        this.name = name;
        this.trafficStats = new TrafficStats();
    }

    public String getSegmentId() {
        return segmentId;
    }

    public String getName() {
        return name;
    }

    public TrafficStats getTrafficStats() {
        return trafficStats;
    }

    public boolean isCongested() {
        return congested;
    }

    public void setCongested(boolean congested) {
        this.congested = congested;
    }
}
