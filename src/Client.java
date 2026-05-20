import java.time.LocalDateTime;
import java.util.List;

public class Client {
    public static void main(String[] args) throws Exception {
        Vehicle car = new Vehicle(
                "V1",
                "KL-01-1234",
                VehicleType.CAR,
                VehicleStatus.AVAILABLE
        );

        VehicleInventory inventory =
                new VehicleInventory(List.of(car));

        ReservationRepository reservationRepository =
                new ReservationRepository();

        ReservationService reservationService =
                new ReservationService(inventory, reservationRepository);

        PricingStrategy pricingStrategy =
                new HourlyPricingStrategy(100.0);

        BillingService billingService =
                new BillingService(pricingStrategy);

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

        Payment payment =
                billingService.generatePayment(reservation);

        System.out.println(payment);

    }
}
