import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private Guest guest;
    private Room room;
    private ReservationState reservationState;


    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(
            String reservationId,
            Guest guest,
            Room room,
            LocalDate checkInDate,
            LocalDate checkOutDate
    ) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationState = new ConfirmedState();
    }

    public void checkIn(){
        reservationState.checkIn(this);
    }
    public void checkOut(){
        reservationState.checkOut(this);
    }
    public void cancel(){
        reservationState.cancel(this);
    }

    // Check if the reservation is blocked by another reservation
    public boolean blocksAvailability() {
        return reservationState instanceof ConfirmedState
                || reservationState instanceof CheckedInState;
    }

    public void setReservationState(ReservationState reservationState) {
        this.reservationState = reservationState;
    }

    public ReservationState getReservationState() {
        return reservationState;
    }

    // Getter and setter methods

    public String getReservationId() {
        return reservationId;
    }
    public Room getRoom() {
        return room;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public Guest getGuest() {
        return guest;
    }
}
