public class LowerBirthFirst implements SeatAllocationStrategy {
    @Override
    public Seat allocateSeats(Train train, CoachType coachType) {
        // get seat based on coach type from priority queue
        Seat seat = train.pollAvailableSeat(coachType);
        if (seat == null) {
            return null;
        }

        // defensive: poll() should never return an already-reserved seat given Train's queue locking, (ReEntrant locks)
        // but retry a few times if that invariant ever break
        int retries = 3;
        while (retries-- > 0) {
            if (!seat.isReserved()) {
                return seat;
            }

            // Seat was grabbed between poll and here
            // again try to get a seat
            seat = train.pollAvailableSeat(coachType);
            if (seat == null) {
                return null;
            }
        }
        // if we still can't get a seat, return null else return the seat
        return seat.isReserved() ? null : seat;
    }
}
