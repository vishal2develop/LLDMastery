import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coach {
    private final String coachId;
    private final CoachType coachType;
    private final List<Seat> seats = new ArrayList<>();

    public Coach(String coachId, CoachType coachType, int totalSeats) {
        this.coachId = coachId;
        this.coachType = coachType;
        // create seats based on the coach type
        // git birth cycle for the coach type
        BirthType[] berths = BirthType.getBirthCycle(coachType);
        for (int i = 0; i < totalSeats; i++) {
            // get the next birth type in the cycle
            BirthType birth = berths[i % berths.length];
            // create a seat with the seat number and birth type
            seats.add(new Seat(i + 1, birth, this));
        }
    }

    public CoachType getCoachType() {
        return coachType;
    }

    public String getCoachId() {
        return coachId;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }
}
