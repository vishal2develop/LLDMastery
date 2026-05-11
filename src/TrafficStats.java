// Holds live traffic metrics for a road segment.
public class TrafficStats {
    private double totalSpeed;
    private int vehicleCount;

    public void addVehicleUpdate(double speed) {
        totalSpeed += speed;
        vehicleCount++;
    }

    public double getAverageSpeed() {
        if (vehicleCount == 0) {
            return 0;
        }

        return totalSpeed / vehicleCount;
    }
}
