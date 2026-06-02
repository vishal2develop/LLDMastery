public class Bill {
    private double amount;
    private Reservation reservation;

    public Bill(double amount, Reservation reservation) {
        this.amount = amount;
        this.reservation = reservation;
    }
}
