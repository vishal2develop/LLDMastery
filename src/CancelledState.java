public class CancelledState implements ReservationState{
    @Override
    public void pickupVehicle(Reservation reservation) {
        throw new IllegalStateException("Reservation cancelled");
    }
    @Override
    public void returnVehicle(Reservation reservation) {
        throw new IllegalStateException("Reservation cancelled");
    }
    @Override
    public void cancelReservation(Reservation reservation) {
        throw new IllegalStateException("Reservation cancelled");
    }
}
