public class FirstAvailableStrategy implements SeatAllocationStrategy{
    @Override
    public Seat allocateSeats(Train train, CoachType coachType) {
        // get the first available seat in the coach based on the coach type
        // Time Complexity: O(n)
        for (Coach coach : train.getCoaches()) {
            if(coach.getCoachType() != coachType){
                continue;
            }
            // for the coach type, check if any seat is available
            for(Seat seat:coach.getSeats()){
                if(!seat.isReserved()){
                    return seat;
                }
            }
        }
        return null;
    }
}
