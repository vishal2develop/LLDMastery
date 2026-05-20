public interface ReservationState {
    void pickupVehicle(Reservation reservation);
    void returnVehicle(Reservation reservation);
    void cancelReservation(Reservation reservation);
}
