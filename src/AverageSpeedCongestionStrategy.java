public class AverageSpeedCongestionStrategy implements CongestionDetectionStrategy{
    private double speedThreshold;
    public AverageSpeedCongestionStrategy(double speedThreshold) {
        this.speedThreshold = speedThreshold;
    }
    @Override
    public boolean isCongested(TrafficStats trafficStats) {
        // the road segment is congested if the average speed is below the threshold
        // < speedThreshold = Lower speed = more congestion
        return trafficStats.getAverageSpeed() < speedThreshold;
    }
}
