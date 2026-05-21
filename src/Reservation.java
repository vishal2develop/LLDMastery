import java.time.LocalDateTime;

public class Reservation {
    private String reservationId;
    private ReservationState state;
    private User user;
    private Vehicle vehicle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Reservation(String reservationId, User user, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        this.reservationId = reservationId;
        this.user = user;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        // Initial state after reservation is created
        this.state = new ConfirmedState();
    }

    public void pickUpVehicle(){
        state.pickupVehicle(this);
    }

    public void returnVehicle(){
        state.returnVehicle(this);
    }

    public void cancelReservation(){
        state.cancelReservation(this);
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public ReservationState getState() {
        return state;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getReservationId() {
        return reservationId;
    }
}
