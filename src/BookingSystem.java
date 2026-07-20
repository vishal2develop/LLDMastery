import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BookingSystem {
    private final Map<String, Train> trains = new ConcurrentHashMap<>();

    /**
     * Volatile prevents two problems in double-checked locking:
     * 1. Stops threads from seeing a half-constructed object (prevents instruction reordering)
     * 2. Ensures all threads see the latest value, not a stale cached copy
     */
    private static volatile BookingSystem instance;
    private final SeatAllocationStrategy seatAllocationStrategy;
    private final AtomicLong pnrCounter = new AtomicLong(1000000000L);

    // Map of train number to waitlist
    private final Map<String, Waitlist> waitlists = new ConcurrentHashMap<>();

    private BookingSystem(SeatAllocationStrategy seatAllocationStrategy) {
        this.seatAllocationStrategy = seatAllocationStrategy;
    }

    // Double Check Locking - For thread-safe singleton
    public static BookingSystem getInstance(SeatAllocationStrategy seatAllocationStrategy) {
        // Lazy Initialization - if instance is null, create it else return it
        if (instance == null) {
            // lock on the class
            synchronized (BookingSystem.class) {
                // check again if another thread has already created the instance between the time we checked and now
                if (instance == null) {
                    // create the instance
                    instance = new BookingSystem(seatAllocationStrategy);
                }
            }
        } else if (instance.seatAllocationStrategy != seatAllocationStrategy) {
            throw new IllegalStateException("BookingSystem already initialized with a different strategy");
        }
        return instance;
    }

    public void addTrain(Train train) {
        trains.put(train.getTrainNumber(), train);
        waitlists.put(train.getTrainNumber(), new Waitlist());
    }

    // To book a ticket
    public Ticket bookTicket(Passenger passenger, String trainNumber, CoachType coachType) {
        // get the train based on the train number
        Train train = trains.get(trainNumber);
        if (train == null) {
            throw new IllegalArgumentException("Train not found");
        }
        // try to allocate seats
        Seat seat = seatAllocationStrategy.allocateSeats(train, coachType);

        // if seat is not null and we can reserve it, create a ticket
        if (seat != null && seat.reserve(passenger)) {
            String pnr = "PNR_" + pnrCounter.getAndIncrement();
            return new Ticket(pnr, passenger, seat, train, coachType, BookingStatus.CONFIRMED, Instant.now());

        }
        // If no seat is available, add to waitlist and return a waitlist ticket
        String pnr = "PNR_" + pnrCounter.getAndIncrement();

        Ticket waitlestTicket = new Ticket(pnr, passenger, null, train, coachType, BookingStatus.WAITLISTED, Instant.now());
        Waitlist wl = waitlists.get(trainNumber);
        wl.add(waitlestTicket, coachType);
        return waitlestTicket;
    }

    // To cancel a ticket

    public CancellationReceipt cancelTicket(Ticket ticket) {
        // if the ticket is confirmed and has a seat assigned, return the refund amount
        if (ticket.getStatus() == BookingStatus.CONFIRMED && ticket.getSeat() != null) {
            Seat freedSeat = ticket.getSeat();
            freedSeat.release();

            // Auto-promote waitlist
            // get the waitlist for the train
            Waitlist waitlist = waitlists.get(ticket.getTrain().getTrainNumber());
            // get the first waitlisted entry/passenger for the coach type
            WaitlistEntry waitlistEntry = waitlist.pollNext(ticket.getCoachType());

            // if there is a waitlisted entry, promote it to confirmed
            if (waitlistEntry != null) {
                // reserve the seat + confirm the ticket
                freedSeat.reserve(waitlistEntry.getPassenger());
                waitlistEntry.confirm(freedSeat);
            }
            // if no passengers in the waitlist, offer the seat back to the available seats queue
            else {
                ticket.getTrain().returnSeat(freedSeat);

            }
        }
        // if trying to cancel a waitlisted ticket, remove it from the waitlist
        else if (ticket.getStatus() == BookingStatus.WAITLISTED) {
            Waitlist waitlist = waitlists.get(ticket.getTrain().getTrainNumber());
            waitlist.remove(ticket, ticket.getCoachType());
        }

        // set the tickets status and return cancel receipt
        ticket.setStatus(BookingStatus.CANCELLED);
        return new CancellationReceipt(ticket, Instant.now());
    }

    public Map<String, Train> getTrains() {
        return Collections.unmodifiableMap(trains);
    }
}
