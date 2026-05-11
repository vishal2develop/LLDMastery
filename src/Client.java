import java.util.HashMap;
import java.util.Map;

/** Flow:
 * Vehicle update arrives
 *       ↓
 * Traffic stats updated
 *       ↓
 * Congestion detected
 *       ↓
 * Subscribers notified
 */
public class Client {
    public static void main(String[] args) throws Exception {

        // Step 1: Create road segment
        RoadSegment roadSegment = new RoadSegment("S1", "Main Street");

        // Step 2: Store segments
        Map<String, RoadSegment> roadSegmentMap = Map.of(roadSegment.getSegmentId(), roadSegment);

        // Step 3: Create congestion strategy
        // Means: avg speed < 20 → congested
        CongestionDetectionStrategy strategy = new AverageSpeedCongestionStrategy(20.0);

        // Step 4: Create notification service
        NotificationService notificationService = new NotificationService();

        // Step 5: Create subscribers
        Subscriber subscriber1 = new Subscriber("U1","Vishal");
        Subscriber subscriber2 = new Subscriber("U2","Rahul");

        // Step 6: Subscribe subscribers to road segment
        notificationService.subscribe(roadSegment.getSegmentId(), subscriber1);
        notificationService.subscribe(roadSegment.getSegmentId(), subscriber2);

        // Step 7: Create TrafficManagementService
        TrafficManagementService trafficManagementService = new TrafficManagementService(roadSegmentMap, strategy, notificationService);

        // Step 8: Send vehicle updates
        trafficManagementService.processTrafficUpdate(
                new VehicleLocationUpdate("V1", "S1", 15, System.currentTimeMillis())
        );

        trafficManagementService.processTrafficUpdate(
                new VehicleLocationUpdate("V2", "S1", 10, System.currentTimeMillis())
        );

        trafficManagementService.processTrafficUpdate(
                new VehicleLocationUpdate("V3", "S1", 12, System.currentTimeMillis())
        );


    }
}
