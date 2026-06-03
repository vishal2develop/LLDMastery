import java.util.ArrayList;
import java.util.List;

// stores/fetches reservations
public class ReservationRepository {
    private List<Reservation> reservations = new ArrayList<>();

    public void saveReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getAllReservations() {
        return List.copyOf(reservations);
    }

    public Reservation getReservationById(String reservationId) {
        return reservations.stream()
                .filter(reservation -> reservation.getReservationId()
                        .equals(reservationId))
                .findFirst()
                .orElse(null);
    }

}
