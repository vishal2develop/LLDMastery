/**
 * ConfirmedState - vehicle is reserved and is available for pickup.
 */
public class ConfirmedState implements ReservationState{
    @Override
    public void pickupVehicle(Reservation reservation) {
        reservation.setState(new ActiveState());
        reservation.getVehicle().setStatus(VehicleStatus.RENTED);
        System.out.println("Vehicle Rented");
    }

    @Override
    public void returnVehicle(Reservation reservation) {
        throw new IllegalStateException("Vehicle not picked up yet");
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        reservation.setState(new CancelledState());
        reservation.getVehicle().setStatus(VehicleStatus.AVAILABLE);
        System.out.println("Reservation Cancelled");
    }
}
