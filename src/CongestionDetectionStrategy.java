public interface CongestionDetectionStrategy {
    boolean isCongested(TrafficStats trafficStats);
}
