/**
 * CompletedState - vehicle is returned.
 */
public class CompletedState implements ReservationState{
    @Override
    public void pickupVehicle(Reservation reservation) {
        throw new IllegalStateException("Reservation already completed");
    }

    @Override
    public void returnVehicle(Reservation reservation) {
        throw new IllegalStateException("Vehicle already returned");
    }
    @Override
    public void cancelReservation(Reservation reservation) {
        throw new IllegalStateException("Cannot cancel completed reservation");
    }
}
