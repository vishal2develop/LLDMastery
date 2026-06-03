import java.time.temporal.ChronoUnit;

public class DeluxeRoomPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(Reservation reservation) {
        long numberOfNights = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        return numberOfNights * 150;
    }
}
