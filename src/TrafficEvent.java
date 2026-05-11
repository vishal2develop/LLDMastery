public class TrafficEvent {
    private String roadSegmentId;
    private String message;

    public TrafficEvent(String roadSegmentId, String message) {
        this.roadSegmentId = roadSegmentId;
        this.message = message;
    }

    public String getRoadSegmentId() {
        return roadSegmentId;
    }

    public String getMessage() {
        return message;
    }
}