public class CancelledState implements ReservationState{
    @Override
    public void checkIn(Reservation reservation) {
        throw new IllegalStateException("Cannot check in a cancelled reservation.");
    }
    @Override
    public void checkOut(Reservation reservation) {
        throw new IllegalStateException("Cannot check out a cancelled reservation.");
    }
    @Override
    public void cancel(Reservation reservation) {
        throw new IllegalStateException("Reservation is already cancelled.");
    }
}
