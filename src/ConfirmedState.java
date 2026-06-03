public class ConfirmedState implements ReservationState{
    @Override
    public void checkIn(Reservation reservation) {
        reservation.getRoom().setRoomStatus(RoomStatus.OCCUPIED);
        reservation.setReservationState(new CheckedInState());
        System.out.println("Reservation confirmed for " + reservation.getGuest().getName());
    }
    @Override
    public void checkOut(Reservation reservation) {
        throw new IllegalStateException("Guest must check in first.");

    }
    @Override
    public void cancel(Reservation reservation) {
        reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
        reservation.setReservationState(new CancelledState());
        System.out.println("Reservation cancelled for " + reservation.getGuest().getName());

    }
}
