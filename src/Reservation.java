import java.time.LocalDateTime;

public class Reservation {
    private String reservationId;
    private User user;
    private Vehicle vehicle;
    private ReservationStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Reservation(String reservationId, User user, Vehicle vehicle, ReservationStatus status, LocalDateTime startTime, LocalDateTime endTime) {
        this.reservationId = reservationId;
        this.user = user;
        this.vehicle = vehicle;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
