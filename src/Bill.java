public class Bill {
    private String billId;
    private double amount;
    private Reservation reservation;

    public Bill(String billId,double amount, Reservation reservation) {
        this.billId = billId;
        this.amount = amount;
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", amount=" + amount +
                ", reservationId=" + reservation.getReservationId() +
                '}';
    }
}
