package core;

public enum LogLevel {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5);

    // Each level has a priority assigned to it and is used for comparison
    private final int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Checks if the current log level is greater than or equal to the given log level.
     * @param level - the log level to compare to
     * @return true if the current log level is greater than or equal to the given log level, false otherwise
     */
    public boolean isGreaterThanOrEqualTo(LogLevel level) {
        return priority >= level.priority;
    }
}
