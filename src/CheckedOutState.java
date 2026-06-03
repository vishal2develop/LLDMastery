public class CheckedOutState implements ReservationState{
    @Override
    public void checkIn(Reservation reservation) {
        throw new IllegalStateException("Cannot check in a room that is already checked out.");
    }
    @Override
    public void checkOut(Reservation reservation) {
        throw new IllegalStateException("Room already checked out.");
    }
    @Override
    public void cancel(Reservation reservation) {
        throw new IllegalStateException("Cannot cancel a checked out reservation.");
    }
}
