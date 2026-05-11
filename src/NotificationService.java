import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RoadSegment S1 becomes congested
 *         ↓
 * Find all subscribers of S1
 *         ↓
 * Notify them
 */
public class NotificationService {
    // roadSegmentId -> observers interested in that road segment
    private Map<String, List<TrafficObserver>> roadSegmentSubscribers = new HashMap<>();


    public void subscribe(String roadSegmentId, TrafficObserver observer) {

        List<TrafficObserver> observers =
                roadSegmentSubscribers.get(roadSegmentId);

        // If no list exists for this segment, create one
        if (observers == null) {
            observers = new ArrayList<>();

            roadSegmentSubscribers.put(roadSegmentId, observers);
        }

        // Add observer to the segment's subscriber list
        observers.add(observer);
    }

    public void notifySubscribers(RoadSegment roadSegment){
        List<TrafficObserver> subscribers = roadSegmentSubscribers.get(roadSegment.getSegmentId());
        if(subscribers == null){
            return;
        }
        TrafficEvent event = new TrafficEvent(
                roadSegment.getSegmentId(),
                "Road segment " + roadSegment.getName() + " is congested!"
        );
        for(TrafficObserver subscriber: subscribers){
            subscriber.receiveUpdate(event);
        }
    }
}
