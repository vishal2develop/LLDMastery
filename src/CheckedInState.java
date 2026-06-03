public class CheckedInState implements ReservationState{
    @Override
    public void checkIn(Reservation reservation) {
        throw new IllegalStateException("Room already checked in.");
    }
    @Override
    public void checkOut(Reservation reservation) {
        reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
        reservation.setReservationState(new CheckedOutState());
        System.out.println("Guest " + reservation.getGuest().getName() + " checked out of room " + reservation.getRoom().getRoomNumber());
    }
    @Override
    public void cancel(Reservation reservation) {
        throw new IllegalStateException("Cannot cancel a checked in reservation.");

    }
}
