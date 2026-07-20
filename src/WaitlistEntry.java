public class WaitlistEntry {
    // private final Passenger passenger;
    // private final CoachType coachType;
    // // Volatile ensures that updates to the booking status are immediately visible to all threads,
    // // preventing them from seeing stale, cached values during auto-promotion.
    // private volatile Seat confirmedSeat;
    // private volatile boolean confirmed = false;
    private final Ticket ticket;

    public WaitlistEntry(Ticket ticket) {
        this.ticket = ticket;
    }

    public void confirm(Seat seat) {
        this.ticket.setSeat(seat);
        this.ticket.setStatus(BookingStatus.CONFIRMED);
    }

    // getters
    public Passenger getPassenger() {
        return ticket.getPassenger();
    }

    public CoachType getCoachType() {
        return ticket.getCoachType();
    }

    public Seat getConfirmedSeat() {
        return ticket.getSeat();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean isConfirmed() {
        return ticket.getStatus() == BookingStatus.CONFIRMED;
    }


}
