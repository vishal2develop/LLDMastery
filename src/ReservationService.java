import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationService {
    private RoomInventory roomInventory;
    private ReservationRepository reservationRepository;

    public ReservationService(RoomInventory roomInventory, ReservationRepository reservationRepository) {
        this.roomInventory = roomInventory;
        this.reservationRepository = reservationRepository;
    }

    // For Concurrency-synchronized - To prevent double booking
    public synchronized Reservation reserveRoom(Guest guest, RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        // Step 1: Search for available rooms
        List<Room> rooms = roomInventory.searchAvailableRooms(roomType, checkInDate, checkOutDate,reservationRepository.getAllReservations());
        // Step 2: Pick the first available room and mark it as reserved
        if (rooms.isEmpty()) {
            throw new IllegalStateException("No available rooms for the selected room type.");
        }
        Room room = rooms.getFirst();
        room.setRoomStatus(RoomStatus.RESERVED);


        // Step 3: Create a Reservation and save it to the repository
        Reservation reservation = new Reservation(generateReservationId(),guest,room,checkInDate,checkOutDate);
        reservationRepository.saveReservation(reservation);
        System.out.println("Reservation created for " + guest.getName() + " in room " + room.getRoomNumber() + "-"+room.getRoomType()+" for "+checkInDate+" to "+checkOutDate);
        return reservation;
    }

    private String generateReservationId() {
        return String.valueOf(System.nanoTime());
    }

    public void cancelReservation(Reservation reservation) {
        reservation.cancel();
    }

    public void checkInGuest(Reservation reservation) {
        reservation.checkIn();
    }

    public void checkOutGuest(Reservation reservation) {
        reservation.checkOut();
    }
}
