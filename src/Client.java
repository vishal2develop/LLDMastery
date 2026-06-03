import java.time.LocalDate;
import java.util.Arrays;

public class Client {

    public static void main(String[] args) throws Exception {
        Guest john = new Guest("John");
        Guest jane = new Guest("Jane");
        RoomInventory roomInventory = new RoomInventory();
        ReservationService reservationService = new ReservationService(roomInventory);

        roomInventory.addRoom(new Room(1,RoomType.STANDARD));
        roomInventory.addRoom(new Room(2,RoomType.DELUXE));
        roomInventory.addRoom(new Room(3,RoomType.SUITE));

        // Existing booking: June 3 → June 5
        Reservation r1 = reservationService.reserveRoom(
                john,
                RoomType.DELUXE,
                LocalDate.of(2026, 6, 3),
                LocalDate.of(2026, 6, 5)
        );

        reservationService.checkInGuest(r1);

        // Overlapping booking: June 4 → June 6
        // Should FAIL if only one DELUXE room exists
        try {
            Reservation r2 = reservationService.reserveRoom(
                    jane,
                    RoomType.DELUXE,
                    LocalDate.of(2026, 6, 4),
                    LocalDate.of(2026, 6, 6)
            );
            System.out.println("Reservation created: " + r2.getReservationId());
            reservationService.checkInGuest(r2);
        } catch (Exception e) {
            System.out.println("Reservation failed: " + e.getMessage());
        }

        reservationService.checkOutGuest(r1);
        System.out.println("----------------------------------------------");
        // Non-overlapping booking: June 6 → June 7
        // Should PASS
        Reservation r3 = reservationService.reserveRoom(
                jane,
                RoomType.DELUXE,
                LocalDate.of(2026, 6, 6),
                LocalDate.of(2026, 6, 7)
        );

        reservationService.checkInGuest(r3);

        System.out.println("Reservation created: " + r3.getReservationId());
        reservationService.checkOutGuest(r3);


    }
}
