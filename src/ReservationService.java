import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationService {
    private List<Reservation> reservations;
    private RoomInventory roomInventory;

    public ReservationService(RoomInventory roomInventory) {
        this.roomInventory = roomInventory;
        this.reservations = new ArrayList<>();
    }

    public Reservation reserveRoom(Guest guest, RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        // Step 1: Search for available rooms
        List<Room> rooms = roomInventory.searchAvailableRooms(roomType, checkInDate, checkOutDate,reservations);
        // Step 2: Pick the first available room and mark it as reserved
        if (rooms.isEmpty()) {
            throw new IllegalStateException("No available rooms for the selected room type.");
        }
        Room room = rooms.getFirst();
        room.setRoomStatus(RoomStatus.RESERVED);


        // Step 3: Create a Reservation and add it to the list
        Reservation reservation = new Reservation(generateReservationId(),guest,room,checkInDate,checkOutDate,BookingStatus.CONFIRMED);
        reservations.add(reservation);
        System.out.println("Reservation created for " + guest.getName() + " in room " + room.getRoomNumber() + "-"+room.getRoomType()+" for "+checkInDate+" to "+checkOutDate);
        return reservation;
    }

    private String generateReservationId() {
        return String.valueOf(System.nanoTime());
    }

    public void cancelReservation(Reservation reservation) {
        if (reservation.getBookingStatus() == BookingStatus.CHECKED_IN ||
                reservation.getBookingStatus() == BookingStatus.CHECKED_OUT) {
            throw new IllegalStateException("Cannot cancel active/completed reservation.");
        }
        reservation.setBookingStatus(BookingStatus.CANCELLED);
        reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
        reservations.remove(reservation);
    }

    public void checkInGuest(Reservation reservation) {
        if (reservation.getBookingStatus() != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Reservation is not confirmed.");
        }
        reservation.setBookingStatus(BookingStatus.CHECKED_IN);
        reservation.getRoom().setRoomStatus(RoomStatus.OCCUPIED);
        System.out.println("Guest " + reservation.getGuest().getName() + " checked in to room " + reservation.getRoom().getRoomNumber());
    }

    public void checkOutGuest(Reservation reservation) {
        if (reservation.getBookingStatus() != BookingStatus.CHECKED_IN) {
            throw new IllegalStateException("Guest is not checked in.");
        }
        reservation.setBookingStatus(BookingStatus.CHECKED_OUT);
        reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
        System.out.println("Guest " + reservation.getGuest().getName() + " checked out of room " + reservation.getRoom().getRoomNumber());
        reservations.remove(reservation);
    }
}
