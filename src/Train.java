import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Train {
    private final String trainName;
    private final String trainNumber;
    private final String source;
    private final String destination;

    private final List<Coach> coaches = new CopyOnWriteArrayList<>();

    // For each coach type, a PriorityQueue of available seats. Needed for LowerBirthFirst allocation strategy.
    private final Map<CoachType, PriorityQueue<Seat>> availableSeats = new ConcurrentHashMap<>();

    // For each coach type, a ReentrantLock to synchronize access to the PriorityQueue.
    private final Map<CoachType, ReentrantLock> queueLocks = new ConcurrentHashMap<>();

    public Train(String trainNumber, String trainName, String source, String destination) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        for (CoachType coachType : CoachType.values()) {
            // Create a PriorityQueue for each coach type.
            availableSeats.put(coachType, new PriorityQueue<>());
            // Create a ReentrantLock for each coach type.
            queueLocks.put(coachType, new ReentrantLock());
        }
    }

    public void addCoach(Coach coach) {
        coaches.add(coach);
        ReentrantLock qLock = queueLocks.get(coach.getCoachType());
        qLock.lock();
        try {
            // Create a PriorityQueue for the coach type and add all seats to it.
            PriorityQueue<Seat> seats = availableSeats.get(coach.getCoachType());
            for (Seat seat : coach.getSeats()) {
                seats.offer(seat);
            }
        } finally {
            qLock.unlock();
        }
    }

    public void removeCoach(Coach coach) {
        coaches.remove(coach);
    }

    // get one available seat based on a coach type
    public Seat pollAvailableSeat(CoachType coachType) {
        // lock the queue for the coach type and poll the available seats
        ReentrantLock qLock = queueLocks.get(coachType);
        qLock.lock();
        try {
            return availableSeats.get(coachType).poll();
        } finally {
            qLock.unlock();
        }

    }

    // return a seat to the available seats queue. For cancellation scenarios
    public void returnSeat(Seat seat) {
        // get the coach type of the seat
        CoachType type = seat.getCoach().getCoachType();
        ReentrantLock qLock = queueLocks.get(type);
        qLock.lock();
        try {
            // insert the seat back into the queue as this is a cancellation
            availableSeats.get(type).offer(seat);
        } finally {
            qLock.unlock();
        }
    }

    // getters
    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public List<Coach> getCoaches() {
        // return an unmodifiable list of coaches because we don't want to allow modification of the coaches list'
        return Collections.unmodifiableList(coaches);
    }

    @Override
    public String toString() {
        return String.format("Train[name=%s, number=%s, source=%s, destination=%s]", trainName, trainNumber, source, destination);
    }
}
