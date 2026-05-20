import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy{
    private double pricePerHour;

    public HourlyPricingStrategy(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public double calculatePrice(Reservation reservation) {
        long hours = Duration.between(reservation.getStartTime(), reservation.getEndTime()).toHours();
        return hours * pricePerHour;
    }
}
