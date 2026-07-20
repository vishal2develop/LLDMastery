import java.time.Instant;

public class CancellationReceipt {
    private final Ticket ticket;
    private final Instant cancellationTime;
    private final long refundAmount;

    public CancellationReceipt(Ticket ticket, Instant cancellationTime) {
        this.ticket = ticket;
        this.cancellationTime = cancellationTime;
        this.refundAmount = ticket.calculateRefund();
    }

    // getters
    public Ticket getTicket() {
        return ticket;
    }

    public Instant getCancellationTime() {
        return cancellationTime;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    @Override
    public String toString() {
        return String.format("CancellationReceipt[PNR=%s, refund=%d, time=%s]", ticket.getPnr(), refundAmount, cancellationTime);
    }
}
