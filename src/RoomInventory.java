import java.util.ArrayList;
import java.util.List;

// To search for available rooms
public class RoomInventory {
    private List<Room> rooms = new ArrayList<>();

    public List<Room> searchAvailableRooms(RoomType roomType) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType && room.getRoomStatus() == RoomStatus.AVAILABLE)
                .toList();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }



}
