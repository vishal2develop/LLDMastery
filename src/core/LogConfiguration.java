package core;

/**
 * Represents configuration settings for logging within the system.
 * Provides methods to manage the root log level and adjust logging behavior.
 */
public class LogConfiguration {
    private LogLevel rootLevel;

    public LogConfiguration() {
        this.rootLevel = LogLevel.INFO; // Default level
    }

    public LogConfiguration(LogLevel rootLevel) {
        this.rootLevel = rootLevel;
    }

    public LogLevel getRootLevel() {
        return rootLevel;
    }

    public void setRootLevel(LogLevel rootLevel) {
        this.rootLevel = rootLevel;
    }

    @Override
    public String toString() {
        return String.format("LogConfiguration{rootLevel=%s}", rootLevel);
    }
}
