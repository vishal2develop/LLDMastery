import java.time.LocalDateTime;
import java.util.List;

/**
 * ReservationService orchestrates:
 * - search
 * - reserve
 * - pickup
 * - return
 */
public class ReservationService {
    private VehicleInventory vehicleInventory;
    private ReservationRepository reservationRepository; // store all reservations

    public ReservationService(VehicleInventory vehicleInventory, ReservationRepository reservationRepository) {
        this.vehicleInventory = vehicleInventory;
        this.reservationRepository = reservationRepository;
    }

    public List<Vehicle> searchAvailableVehicles(VehicleType vehicleType) {
        return vehicleInventory.searchAvailableVehicles(vehicleType);
    }

    public Reservation reserveVehicle(User user, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        // Step1: Mark vehicle reserved
        if(!vehicle.reserveVehicle()) {
            throw new IllegalStateException("Vehicle is not available");
        }

        // Step2: Create Reservation
        Reservation reservation = new Reservation(
                generateReservationId(),
                user,
                vehicle,
                startTime,
                endTime
        );

        // Step 3: Save Reservation
        reservationRepository.save(reservation);
        System.out.println("Vehicle Reserved");
        return reservation;
    }

    public void pickupVehicle(Reservation reservation) {
        reservation.pickUpVehicle();
    }

    public void returnVehicle(Reservation reservation) {
        reservation.returnVehicle();
    }

    public void cancelReservation(Reservation reservation) {
        reservation.cancelReservation();
    }

    private String generateReservationId() {
        return String.valueOf(System.nanoTime());
    }
}
