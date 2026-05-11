import java.util.Map;

public class TrafficManagementService {
    // Map of road segments: id -> RoadSegment
    private Map<String, RoadSegment> roadSegmentMap;
    private CongestionDetectionStrategy congestionDetectionStrategy;
    private NotificationService notificationService;

    public TrafficManagementService(Map<String,RoadSegment> roadSegmentMap,CongestionDetectionStrategy congestionDetectionStrategy, NotificationService notificationService) {
        this.roadSegmentMap = roadSegmentMap;
        this.congestionDetectionStrategy = congestionDetectionStrategy;
        this.notificationService = notificationService;
    }

    public void processTrafficUpdate(VehicleLocationUpdate update) {
        // get the road segment for the update
        RoadSegment roadSegment = roadSegmentMap.get(update.getRoadSegmentId());

        if (roadSegment == null) {
            System.out.println("Road segment not found: " + update.getRoadSegmentId());
            return;
        }

        // update the traffic stats for the road segment
        roadSegment.getTrafficStats().addVehicleUpdate(update.getSpeed());

        // check if the road segment is congested
        boolean isCurrentlyCongested = congestionDetectionStrategy.isCongested(roadSegment.getTrafficStats());

        // if the road segment is congested, and it wasn't previously, set the congestion flag and notify subscribers
        if (isCurrentlyCongested && !roadSegment.isCongested()) {
            roadSegment.setCongested(true);
            // notify the subscribers
            notificationService.notifySubscribers(roadSegment);
        }

        // if the road segment is no longer congested, reset the congestion flag
        if (!isCurrentlyCongested && roadSegment.isCongested()) {
            roadSegment.setCongested(false);
        }
    }
}
