import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Waitlist {

    // Concurrent Map to store waitlist entries by coach type. This is a concurrent queue to ensure thread-safe access.
    // ConcurrentHashMap is used to avoid the need for synchronization.
    // ConcurrentLinkedQueue is used to maintain the order of entries in the queue. Follows FIFO principle.
    private final Map<CoachType, ConcurrentLinkedQueue<WaitlistEntry>> waitlistQueue = new ConcurrentHashMap<>();

    public Waitlist() {
        // Initialize the waitlist with an empty queue for each coach type.
        for (CoachType type : CoachType.values()) {
            waitlistQueue.put(type, new ConcurrentLinkedQueue<>());
        }
    }

    public void add(Ticket ticket, CoachType coachType) {
        // offer vs add - offer returns true if the element was added, false if it was not added because the queue was full.
        // add throws an exception if the queue is full.
        waitlistQueue.get(coachType).offer(new WaitlistEntry(ticket));
    }

    // Get the next entry in the waitlist for a given coach type.
    public WaitlistEntry pollNext(CoachType coachType) {
        return waitlistQueue.get(coachType).poll();
    }

    public boolean remove(Ticket ticket,CoachType coachType) {
        // This method is used to remove a specific waitlist ticket from the waitlist.
        // removeIf returns true if any elements were removed, false otherwise.
        return waitlistQueue.get(coachType).removeIf(e->e.getTicket().equals(ticket));
    }

    public int getWaitlistSize(CoachType coachType) {
        return waitlistQueue.get(coachType).size();
    }



}
