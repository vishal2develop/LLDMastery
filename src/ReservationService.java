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
        vehicle.setStatus(VehicleStatus.RESERVED);

        // Step2: Create Reservation
        Reservation reservation = new Reservation(
                generateReservationId(),
                user,
                vehicle,
                ReservationStatus.CONFIRMED,
                startTime,
                endTime
        );

        // Step 3: Save Reservation
        reservationRepository.save(reservation);
        System.out.println("Vehicle Reserved");
        return reservation;
    }

    public void pickupVehicle(Reservation reservation) {
        // A vehicle can only be picked up if it is reserved and confirmed
        if(reservation.getStatus() != ReservationStatus.CONFIRMED){
            throw new IllegalStateException("Reservation is not confirmed");
        }

        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.getVehicle().setStatus(VehicleStatus.RENTED);
        System.out.println("Vehicle Rented");
    }

    public void returnVehicle(Reservation reservation) {
        if(reservation.getStatus() != ReservationStatus.ACTIVE){
            throw new IllegalStateException("Vehicle is not currently rented");
        }
        reservation.setStatus(ReservationStatus.COMPLETED);
        reservation.getVehicle().setStatus(VehicleStatus.AVAILABLE);
        System.out.println("Vehicle Returned");
    }

    public void cancelReservation(Reservation reservation) {
        if(reservation.getStatus() == ReservationStatus.COMPLETED || reservation.getStatus() == ReservationStatus.ACTIVE){
            throw new IllegalStateException("Cannot cancel a completed/active reservation");
        }
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.getVehicle().setStatus(VehicleStatus.AVAILABLE);
        System.out.println("Reservation Cancelled");
    }

    private String generateReservationId() {
        return String.valueOf(System.nanoTime());
    }





}
