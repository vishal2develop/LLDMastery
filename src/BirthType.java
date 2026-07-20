public enum BirthType {
    // berth types with their corresponding priority
    LOWER(1),
    MIDDLE(3),
    UPPER(5),
    SIDE_LOWER(2),
    SIDE_UPPER(4);

    // priority field used by PriorityQueue to prefer lower berths for LowerBirthFirst allocation strategy
    private int priority;

    // constructor to set priority
    BirthType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    // returns the repeating berth pattern for a coach type (e.g., Sleeper follows LOWER, MIDDLE, UPPER, LOWER, MIDDLE, UPPER, SIDE_LOWER, SIDE_UPPER).
    public static BirthType[] getBirthCycle(CoachType coachType) {
        switch (coachType) {
            case SLEEPER:
                return new BirthType[]{
                        LOWER, MIDDLE, UPPER,
                        LOWER, MIDDLE, UPPER,
                        SIDE_LOWER, SIDE_UPPER
                };
            case AC:
                return new BirthType[]{
                        LOWER, UPPER,
                        LOWER, UPPER,
                        SIDE_LOWER, SIDE_UPPER
                };

            case GENERAL:
                return new BirthType[]{LOWER};
            default:
                return new BirthType[]{LOWER};
        }
    }
}
