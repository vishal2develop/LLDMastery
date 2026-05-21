import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Client {

    public static void main(String[] args) throws Exception {
        testNormalRentalFlow();
        System.out.println();
        testConcurrentReservationFlow();
    }

    private static void testNormalRentalFlow() {
        System.out.println("=== Normal Rental Flow ===");

        Vehicle car = new Vehicle(
                "V1",
                "KL-01-1234",
                VehicleType.CAR,
                VehicleStatus.AVAILABLE
        );

        VehicleInventory inventory = new VehicleInventory(List.of(car));
        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService =
                new ReservationService(inventory, reservationRepository);

        BillingService billingService =
                new BillingService(new HourlyPricingStrategy(100.0));

        User user = new User("vishal");

        List<Vehicle> availableCars =
                reservationService.searchAvailableVehicles(VehicleType.CAR);

        Reservation reservation =
                reservationService.reserveVehicle(
                        user,
                        availableCars.getFirst(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(5)
                );

        reservationService.pickupVehicle(reservation);
        reservationService.returnVehicle(reservation);

        Payment payment = billingService.generatePayment(reservation);
        System.out.println(payment);
    }

    private static void testConcurrentReservationFlow() throws Exception {
        System.out.println("=== Concurrent Reservation Flow ===");

        Vehicle car = new Vehicle(
                "V2",
                "KL-01-9999",
                VehicleType.CAR,
                VehicleStatus.AVAILABLE
        );

        VehicleInventory inventory = new VehicleInventory(List.of(car));
        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService =
                new ReservationService(inventory, reservationRepository);

        BillingService billingService =
                new BillingService(new HourlyPricingStrategy(100.0));

        List<User> users = List.of(
                new User("vishal"),
                new User("rahul"),
                new User("amit")
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Reservation>> futures = new ArrayList<>();

        for (User user : users) {
            futures.add(executor.submit(() ->
                    reservationService.reserveVehicle(
                            user,
                            car,
                            LocalDateTime.now(),
                            LocalDateTime.now().plusHours(5)
                    )
            ));
        }

        executor.shutdown();

        Reservation successfulReservation = null;

        for (Future<Reservation> future : futures) {
            try {
                Reservation reservation = future.get();
                successfulReservation = reservation;
                System.out.println("Reservation success: " + reservation.getReservationId());
            } catch (Exception e) {
                System.out.println("Reservation failed: " + e.getCause().getMessage());
            }
        }

        if (successfulReservation != null) {
            reservationService.pickupVehicle(successfulReservation);
            reservationService.returnVehicle(successfulReservation);

            Payment payment = billingService.generatePayment(successfulReservation);
            System.out.println(payment);
        }
    }
}