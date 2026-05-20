public class Payment {
    private String paymentId;
    private Reservation reservation;
    private double amount;

    private PaymentStatus paymentStatus;

    public Payment(String paymentId, Reservation reservation, double amount, PaymentStatus paymentStatus) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", reservationId='" + reservation.getReservationId() + '\'' +
                ", amount=" + amount +
                ", status=" + paymentStatus +
                '}';
    }

}
