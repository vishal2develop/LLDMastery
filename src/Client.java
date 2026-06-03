import java.time.LocalDate;

public class Client {

    public static void main(String[] args) {
        testDateOverlapAvailability();
    }

    private static void testDateOverlapAvailability() {
        System.out.println("=== Date Overlap Availability ===");

        ReservationService reservationService = setupHotel();
        Guest john = new Guest("John");
        Guest jane = new Guest("Jane");

        LocalDate jun3 = LocalDate.of(2026, 6, 3);
        LocalDate jun5 = LocalDate.of(2026, 6, 5);
        LocalDate jun4 = LocalDate.of(2026, 6, 4);
        LocalDate jun6 = LocalDate.of(2026, 6, 6);
        LocalDate jun7 = LocalDate.of(2026, 6, 7);

        System.out.println("\n-- Overlapping booking while room is occupied --");
        Reservation johnStay = reserveAndCheckIn(
                reservationService, john, RoomType.DELUXE, jun3, jun5);
        attemptReserve(
                reservationService, jane, RoomType.DELUXE, jun4, jun6,
                "Should fail: only one DELUXE room, dates overlap Jun 4–6");

        System.out.println("\n-- Non-overlapping booking after check-out --");
        reservationService.checkOutGuest(johnStay);
        Reservation janeStay = reserveAndCheckIn(
                reservationService, jane, RoomType.DELUXE, jun6, jun7);
        reservationService.checkOutGuest(janeStay);
        System.out.println("Reservation completed: " + janeStay.getReservationId());
    }

    private static ReservationService setupHotel() {
        RoomInventory roomInventory = new RoomInventory();
        roomInventory.addRoom(new Room(1, RoomType.STANDARD));
        roomInventory.addRoom(new Room(2, RoomType.DELUXE));
        roomInventory.addRoom(new Room(3, RoomType.SUITE));
        return new ReservationService(roomInventory);
    }

    private static Reservation reserveAndCheckIn(
            ReservationService service,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut) {
        Reservation reservation = service.reserveRoom(guest, roomType, checkIn, checkOut);
        service.checkInGuest(reservation);
        return reservation;
    }

    private static void attemptReserve(
            ReservationService service,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut,
            String expectation) {
        System.out.println("Expectation: " + expectation);
        try {
            Reservation reservation = service.reserveRoom(guest, roomType, checkIn, checkOut);
            service.checkInGuest(reservation);
            System.out.println("Unexpected success: " + reservation.getReservationId());
        } catch (Exception e) {
            System.out.println("Reservation failed: " + e.getMessage());
        }
    }
}
