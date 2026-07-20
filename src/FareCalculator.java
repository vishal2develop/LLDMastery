import java.util.Map;

public class FareCalculator {
    // maintain a map of fare rates for different coach types
    private static final Map<CoachType, Long> BASE_FARES = Map.of(
            CoachType.SLEEPER, 500L,
            CoachType.AC, 1500L,
            CoachType.GENERAL, 150L
    );

    // Maps berth types to price multipliers (e.g., lower berths cost more due to higher demand).
    private static final Map<BirthType, Double> BERTH_MULTIPLIERS = Map.of(
            BirthType.LOWER, 1.2,
            BirthType.MIDDLE, 1.0,
            BirthType.UPPER, 0.9,
            BirthType.SIDE_LOWER, 1.1,
            BirthType.SIDE_UPPER, 0.85
    );

    public static long calculate(Train train, CoachType coachType, Seat seat) {
        // get the base fare for the coach type or default to 500 if not found
        long basefare = BASE_FARES.getOrDefault(coachType, 500L);
        // if no seat exists in case of waitlist, return the base fare as the passenger still has to pay the fare
        // the birth based multiplier cannot be applied as the seat is not yet allocated
        if (seat == null) {
            return basefare;
        }

        // for confirmed ticket scenario
        // get the multiplier for the seat's birth type or default to 1.0(middle berth) if not found'
        double multiplier = BERTH_MULTIPLIERS.getOrDefault(seat.getBirthType(), 1.0);
        // final price = basefare * multiplier
        return (long) (basefare * multiplier);
    }

}
