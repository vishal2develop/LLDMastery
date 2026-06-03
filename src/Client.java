import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

    public static void main(String[] args) throws Exception {
        testDateOverlapAvailability();
        System.out.println();
        testConcurrentReservation();
        // testReservationStateTransitions();
    }

    private static void testDateOverlapAvailability() {
        System.out.println("=== Date Overlap Availability ===");

        HotelService hotel = setupHotel();
        Guest john = new Guest("John");
        Guest jane = new Guest("Jane");

        LocalDate jun3 = LocalDate.of(2026, 6, 3);
        LocalDate jun5 = LocalDate.of(2026, 6, 5);
        LocalDate jun4 = LocalDate.of(2026, 6, 4);
        LocalDate jun6 = LocalDate.of(2026, 6, 6);
        LocalDate jun7 = LocalDate.of(2026, 6, 7);

        System.out.println("\n-- Overlapping booking while room is occupied --");
        Reservation johnStay = reserveAndCheckIn(hotel, john, RoomType.DELUXE, jun3, jun5);
        attemptReserve(hotel, jane, RoomType.DELUXE, jun4, jun6,
                "Should fail: only one DELUXE room, dates overlap Jun 4–6");

        System.out.println("\n-- Non-overlapping booking after check-out --");
        Bill johnBill = hotel.checkOutGuest(johnStay);
        System.out.println("John's bill: " + johnBill);

        Reservation janeStay = reserveAndCheckIn(hotel, jane, RoomType.DELUXE, jun6, jun7);
        Bill janeBill = hotel.checkOutGuest(janeStay);
        System.out.println("Reservation completed: " + janeStay.getReservationId());
        System.out.println("Jane's bill: " + janeBill);
    }

    private static void testConcurrentReservation() throws Exception {
        System.out.println("=== Concurrent Reservation ===");

        HotelService hotel = setupSingleDeluxeHotel();
        LocalDate checkIn = LocalDate.of(2026, 8, 1);
        LocalDate checkOut = LocalDate.of(2026, 8, 3);
        List<Guest> guests = List.of(new Guest("A"), new Guest("B"), new Guest("C"));

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Reservation>> futures = new ArrayList<>();

        for (Guest guest : guests) {
            futures.add(executor.submit(() ->
                    hotel.reserveRoom(guest, RoomType.DELUXE, checkIn, checkOut)));
        }
        executor.shutdown();

        int successCount = 0;
        for (Future<Reservation> future : futures) {
            try {
                Reservation reservation = future.get();
                successCount++;
                System.out.println("Reservation success: " + reservation.getReservationId()
                        + " (room " + reservation.getRoom().getRoomNumber() + ")");
            } catch (Exception e) {
                Throwable cause = e.getCause() != null ? e.getCause() : e;
                System.out.println("Reservation failed: " + cause.getMessage());
            }
        }
        System.out.println("Successful reservations: " + successCount + " (expected 1)");
    }

    private static HotelService setupSingleDeluxeHotel() {
        RoomInventory roomInventory = new RoomInventory();
        ReservationRepository reservationRepository = new ReservationRepository();
        roomInventory.addRoom(new Room(2, RoomType.DELUXE));
        ReservationService reservationService =
                new ReservationService(roomInventory, reservationRepository);
        return new HotelService(reservationService, new BillingService());
    }

    private static void testReservationStateTransitions() {
        System.out.println("=== Reservation State Transitions ===");

        HotelService hotel = setupHotel();
        Guest guest = new Guest("Alex");
        LocalDate checkIn = LocalDate.of(2026, 7, 1);
        LocalDate checkOut = LocalDate.of(2026, 7, 3);

        System.out.println("\n-- Check-out before check-in --");
        Reservation confirmed = reserve(hotel, guest, RoomType.STANDARD, checkIn, checkOut);
        attempt("Should fail: guest must check in first",
                () -> hotel.checkOutGuest(confirmed));

        System.out.println("\n-- Cancel after check-in --");
        Reservation checkedIn = reserveAndCheckIn(hotel, guest, RoomType.SUITE, checkIn, checkOut);
        attempt("Should fail: cannot cancel a checked-in reservation",
                () -> hotel.cancelReservation(checkedIn));

        System.out.println("\n-- Double check-in --");
        attempt("Should fail: room already checked in",
                () -> hotel.checkInGuest(checkedIn));

        System.out.println("\n-- Cancel while confirmed --");
        Reservation toCancel = reserve(hotel, guest, RoomType.DELUXE, checkIn, checkOut);
        hotel.cancelReservation(toCancel);
        Reservation afterCancel = reserve(hotel, guest, RoomType.DELUXE, checkIn, checkOut);
        System.out.println("Rebooked after cancel: " + afterCancel.getReservationId());
    }

    private static HotelService setupHotel() {
        RoomInventory roomInventory = new RoomInventory();
        ReservationRepository reservationRepository = new ReservationRepository();
        roomInventory.addRoom(new Room(1, RoomType.STANDARD));
        roomInventory.addRoom(new Room(2, RoomType.DELUXE));
        roomInventory.addRoom(new Room(3, RoomType.SUITE));

        ReservationService reservationService =
                new ReservationService(roomInventory, reservationRepository);
        BillingService billingService = new BillingService();
        return new HotelService(reservationService, billingService);
    }

    private static Reservation reserve(
            HotelService hotel,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut) {
        return hotel.reserveRoom(guest, roomType, checkIn, checkOut);
    }

    private static Reservation reserveAndCheckIn(
            HotelService hotel,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut) {
        Reservation reservation = reserve(hotel, guest, roomType, checkIn, checkOut);
        hotel.checkInGuest(reservation);
        return reservation;
    }

    private static void attemptReserve(
            HotelService hotel,
            Guest guest,
            RoomType roomType,
            LocalDate checkIn,
            LocalDate checkOut,
            String expectation) {
        attempt(expectation, () -> {
            Reservation reservation = reserve(hotel, guest, roomType, checkIn, checkOut);
            hotel.checkInGuest(reservation);
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
