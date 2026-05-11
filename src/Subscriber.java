// A user/app interested in traffic alerts for a road segment.
public class Subscriber implements TrafficObserver {
    private String subscriberId;
    private String name;

    public Subscriber(String subscriberId, String name) {
        this.subscriberId = subscriberId;
        this.name = name;
    }

    @Override
    public void receiveUpdate(TrafficEvent event) {
        System.out.println(
                name + " received alert: " + event.getMessage()
        );
    }

    public String getName() {
        return name;
    }

    public String getSubscriberId() {
        return subscriberId;
    }
}
