import java.time.LocalDate;

public class Client {

    public static void main(String[] args) {
        testDateOverlapAvailability();
        System.out.println();
        testReservationStateTransitions();
    }

    private static void testDateOverlapAvailability() {
        System.out.println("=== Date Overlap Availability ===");

        ReservationService service = setupHotel();
        Guest john = new Guest("John");
        Guest jane = new Guest("Jane");

        LocalDate jun3 = LocalDate.of(2026, 6, 3);
        LocalDate jun5 = LocalDate.of(2026, 6, 5);
        LocalDate jun4 = LocalDate.of(2026, 6, 4);
        LocalDate jun6 = LocalDate.of(2026, 6, 6);
        LocalDate jun7 = LocalDate.of(2026, 6, 7);

        System.out.println("\n-- Overlapping booking while room is occupied --");
        Reservation johnStay = reserveAndCheckIn(service, john, RoomType.DELUXE, jun3, jun5);
        attemptReserve(service, jane, RoomType.DELUXE, jun4, jun6,
                "Should fail: only one DELUXE room, dates overlap Jun 4–6");

        System.out.println("\n-- Non-overlapping booking after check-out --");
        service.checkOutGuest(johnStay);
        Reservation janeStay = reserveAndCheckIn(service, jane, RoomType.DELUXE, jun6, jun7);
        service.checkOutGuest(janeStay);
        System.out.println("Reservation completed: " + janeStay.getReservationId());
    }

    private static void testReservationStateTransitions() {
        System.out.println("=== Reservation State Transitions ===");

        ReservationService service = setupHotel();
        Guest guest = new Guest("Alex");
        LocalDate checkIn = LocalDate.of(2026, 7, 1);
        LocalDate checkOut = LocalDate.of(2026, 7, 3);

        System.out.println("\n-- Check-out before check-in --");
        Reservation confirmed = reserve(service, guest, RoomType.STANDARD, checkIn, checkOut);
        attempt("Should fail: guest must check in first",
                () -> service.checkOutGuest(confirmed));

        System.out.println("\n-- Cancel after check-in --");
        Reservation checkedIn = reserveAndCheckIn(service, guest, RoomType.SUITE, checkIn, checkOut);
        attempt("Should fail: cannot cancel a checked-in reservation",
                () -> service.cancelReservation(checkedIn));

        System.out.println("\n-- Double check-in --");
        attempt("Should fail: room already checked in",
                () -> service.checkInGuest(checkedIn));

        System.out.println("\n-- Cancel while confirmed --");
        Reservation toCancel = reserve(service, guest, RoomType.DELUXE, checkIn, checkOut);
        service.cancelReservation(toCancel);
        Reservation afterCancel = reserve(service, guest, RoomType.DELUXE, checkIn, checkOut);
        System.out.println("Rebooked after cancel: " + afterCancel.getReservationId());
    }

    private static ReservationService setupHotel() {
        RoomInventory roomInventory = new RoomInventory();
        ReservationRepository reservationRepository = new ReservationRepository();
        roomInventory.addRoom(new Room(1, RoomType.STANDARD));
        roomInventory.addRoom(new Room(2, RoomType.DELUXE));
        roomInventory.addRoom(new Room(3, RoomType.SUITE));
        return new ReservationService(roomInventory, reservationRepository);
    }

    private static Reservation reserve(
            ReservationService service,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut) {
        return service.reserveRoom(guest, roomType, checkIn, checkOut);
    }

    private static Reservation reserveAndCheckIn(
            ReservationService service,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut) {
        Reservation reservation = reserve(service, guest, roomType, checkIn, checkOut);
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
        attempt(expectation, () -> {
            Reservation reservation = reserve(service, guest, roomType, checkIn, checkOut);
            service.checkInGuest(reservation);
        });
    }

    private static void attempt(String expectation, Runnable action) {
        System.out.println("Expectation: " + expectation);
        try {
            action.run();
            System.out.println("Unexpected success");
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}
