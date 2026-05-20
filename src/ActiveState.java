/**
 * ActiveState - vehicle is picked up and is in use.
 */
public class ActiveState implements ReservationState{

    @Override
    public void pickupVehicle(Reservation reservation) {
        throw new IllegalStateException("Vehicle already picked up");
    }
    @Override
    public void returnVehicle(Reservation reservation) {
        reservation.setState(new CompletedState());
        reservation.getVehicle().setStatus(VehicleStatus.AVAILABLE);
        System.out.println("Vehicle Returned");
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        throw new IllegalStateException("Cannot cancel active reservation");
    }
}
