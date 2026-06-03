import java.time.temporal.ChronoUnit;

public class StandardRoomPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(Reservation reservation) {
        // calculate price based on number of nights. 100 = Room rate per night
        long numberOfNights = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        return numberOfNights * 100;
    }
}
