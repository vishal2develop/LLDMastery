import java.time.LocalDate;

public class Client {

    public static void main(String[] args) throws Exception {
        Guest guest = new Guest("John");
        RoomInventory roomInventory = new RoomInventory();
        ReservationService reservationService = new ReservationService(roomInventory);

        roomInventory.addRoom(new Room(1,RoomType.STANDARD));
        roomInventory.addRoom(new Room(2,RoomType.DELUXE));
        roomInventory.addRoom(new Room(3,RoomType.SUITE));

        Reservation reservation = reservationService.reserveRoom(guest,RoomType.DELUXE, LocalDate.now(),LocalDate.now().plusDays(1));

        reservationService.checkInGuest(reservation);
        // mock sleeping
        Thread.sleep(1000);
        reservationService.checkOutGuest(reservation);

        System.out.println("Room Status "+reservation.getRoom().getRoomStatus());


    }
}
