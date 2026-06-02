import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private Guest guest;
    private Room room;
    private BookingStatus bookingStatus;


    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(
            String reservationId,
            Guest guest,
            Room room,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            BookingStatus bookingStatus
    ) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
    }

    // Getter methods

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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Guest getGuest() {
        return guest;
    }
}
