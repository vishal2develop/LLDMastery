import java.util.concurrent.locks.ReentrantLock;

public class Seat implements Comparable<Seat> {
    private final int seatNumber;
    private final BirthType birthType;
    private final Coach coach;
    private volatile boolean reserved = false;
    private Passenger passenger;

    // ReentrantLock is used to synchronize access to the seat for thread safety.
    // This lock is used to prevent multiple threads from reserving the same seat at the same time.
    private final ReentrantLock lock = new ReentrantLock();

    public Seat(int seatNumber, BirthType birthType, Coach coach) {
        this.seatNumber = seatNumber;
        this.birthType = birthType;
        this.coach = coach;
    }

    public boolean reserve(Passenger passenger) {
        // try to acquire the lock, if not available, return false
        if (!lock.tryLock()) {
            return false;
        }
        try {
            if (reserved) {
                return false;
            }
            this.passenger = passenger;
            this.reserved = true;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        // lock the seat to prevent concurrent modification
        lock.lock();
        try {
            this.passenger = null;
            this.reserved = false;
        } finally {
            lock.unlock();
        }

    }

    // PriorityQueue uses compareTo to sort the seats based on the following rules:
    // 1. Lower birth type gets higher priority
    // 2. Tie - lower seat number
    @Override
    public int compareTo(Seat o) {
        // Rule 1: compare by birth type. Lower gets highest priority
        int birthCompare = Integer.compare(this.birthType.getPriority(), o.birthType.getPriority());
        if (birthCompare != 0) {
            return birthCompare;
        }
        // Rule 2: Tie - compare by seat number
        return Integer.compare(this.seatNumber, o.seatNumber);

    }

    // getters
    public Coach getCoach() {
        return coach;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public boolean isReserved() {
        return reserved;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public BirthType getBirthType() {
        return birthType;
    }
}
