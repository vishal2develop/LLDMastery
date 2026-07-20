public interface SeatAllocationStrategy {
    Seat allocateSeats(Train train, CoachType coachType);
}
