import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// To search for available rooms based on room type, check-in date, check-out date, and reservations
public class RoomInventory {
    private List<Room> rooms = new ArrayList<>();

    public List<Room> searchAvailableRooms(RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate,List<Reservation> reservations) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .filter(room -> isRoomAvailable(room,checkInDate,checkOutDate,reservations))
                .toList();
    }

    public boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkOutDate,List<Reservation> reservations) {
        for (Reservation reservation : reservations) {

            // Check if the reservation is for the same room
            if(reservation.getRoom()!=room){
                continue;
            }

            // Check if the reservation is cancelled or checked out
            if(reservation.getBookingStatus() == BookingStatus.CANCELLED || reservation.getBookingStatus() == BookingStatus.CHECKED_OUT){
                continue;
            }

            // Check if the reservation overlaps with the user's dates
            boolean overlaps = checkInDate.isBefore(reservation.getCheckOutDate()) && checkOutDate.isAfter(reservation.getCheckInDate());
            if(overlaps){
                return false;
            }
        }
        return true;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }



}
