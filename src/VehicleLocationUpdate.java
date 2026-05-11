
// VehicleLocationUpdate represents a real-time traffic event carrying the minimum data needed for congestion detection.
public class VehicleLocationUpdate {
    private String vehicleId;
    private String roadSegmentId;
    private double speed;
    private long timestamp;

    public VehicleLocationUpdate(String vehicleId, String roadSegmentId, double speed, long timestamp) {
        this.vehicleId = vehicleId;
        this.roadSegmentId = roadSegmentId;
        this.speed = speed;
        this.timestamp = timestamp;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getRoadSegmentId() {
        return roadSegmentId;
    }

    public double getSpeed() {
        return speed;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
