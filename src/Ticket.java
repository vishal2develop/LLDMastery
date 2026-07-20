import java.time.Duration;
import java.time.Instant;

public class Ticket {
    private final String pnr;
    private final Passenger passenger;
    private volatile Seat seat; // null if the ticket is waitlisted
    private final Train train;
    private final CoachType coachType;
    // Volatile ensures that updates to the booking status are immediately visible to all threads,
    // preventing them from seeing stale, cached values during auto-promotion.
    private volatile BookingStatus status;
    private final Instant bookingTime;
    private final long fare; // Store the fare at booking time

    public Ticket(String pnr, Passenger passenger, Seat seat, Train train, CoachType coachType, BookingStatus status, Instant bookingTime) {
        this.pnr = pnr;
        this.passenger = passenger;
        this.seat = seat;
        this.train = train;
        this.coachType = coachType;
        this.status = status;
        this.bookingTime = bookingTime;
        this.fare = calculateFare();
    }

    public long calculateFare() {
        return FareCalculator.calculate(train, coachType, seat);
    }

    public long calculateRefund() {

        // 1. calculate the hours elapsed since the booking time
        long hours = Duration.between(bookingTime, Instant.now()).toHours();
        // Apply hour based refund rules
        if (hours < 24) {
            return (long) (fare * 0.5); // 50% refund if cancelled within 24 hours of booking the ticket
        }
        if (hours < 48) {
            return (long) (fare * 0.75); // 75% refund if cancelled within 48 hours of booking the ticket
        }
        return fare; // Full refund if cancelled after 48 hours of booking the ticket
    }

    // getters
    public String getPnr() {
        return pnr;
    }

    public long getFare() {
        return fare;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public Train getTrain() {
        return train;
    }

    public CoachType getCoachType() {
        return coachType;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Instant getBookingTime() {
        return bookingTime;
    }



    // setters

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // needed to set the seat after the waitlist ticket is promoted to a confirmed seat
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        String seatInfo = (seat != null)
                ? String.format("Seat: %s-%s", seat.getCoach().getCoachId(), seat.getSeatNumber())
                : "Seat: WAITLISTED";

        return String.format(
                "Ticket[PNR=%s, Passenger=%s, Train=%s, Coach=%s, %s, Status=%s, Fare=₹%d, BookedAt=%s]",
                pnr,
                passenger.getName(),
                train.getTrainNumber(),
                coachType,
                seatInfo,
                status,
                fare,
                bookingTime
        );
    }


}
