package core;

import appender.ConsoleAppender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main implementation of the Logger interface.
 * Provides thread-safe logging with support for multiple appenders and filters.
 */
public class LoggerImpl implements Logger {
    private final String name;
    private LogLevel level;
    private List<LogFilter> filters;
    private List<LogAppender> appenders;

    public LoggerImpl() {
        this("DefaultLogger");
    }

    public LoggerImpl(String name) {
        this(name, true); // Default to adding console appender
    }

    public LoggerImpl(String name, boolean addDefaultAppender) {
        this.name = name;
        this.level = LogLevel.DEBUG;
        this.appenders = Collections.synchronizedList(new ArrayList<>());
        this.filters = Collections.synchronizedList(new ArrayList<>());

        if (addDefaultAppender) {
            addAppender(new ConsoleAppender());
        }
    }

    public LoggerImpl(String name, LogConfiguration config) {
        this(name);
        this.level = config.getRootLevel();
    }

    @Override
    public synchronized void debug(String message) {
        log(LogLevel.DEBUG, message);

    }

    @Override
    public synchronized void info(String message) {
        log(LogLevel.INFO, message);

    }

    @Override
    public synchronized void error(String message) {
        log(LogLevel.ERROR, message);

    }

    @Override
    public synchronized void warn(String message) {
        log(LogLevel.WARN, message);

    }

    @Override
    public synchronized void fatal(String message) {
        log(LogLevel.FATAL, message);

    }

    @Override
    public void log(LogLevel logLevel, String logMessage) {
        // Check if message should be logged based on level
        if (!logLevel.isGreaterThanOrEqualTo(this.level)) {
            return;
        }

        // Create LogMessage using Builder, apply applicable filters and send to appender

        // Build Message
        LogMessage message = new LogMessage.Builder().level(logLevel).message(logMessage).build();

        // Apply filters
        for (LogFilter filter : filters) {
            // if the filter does not allow the message, drop it
            if (!filter.shouldLog(message)) {
                return;
            }
        }

        // Send to appender
        for (LogAppender appender : appenders) {
            // If appender is enabled, send message to it
            if (appender.isEnabled(logLevel)) {
                appender.append(message);
            }
        }
    }

    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public void addAppender(LogAppender appender) {
        this.appenders.add(appender);
    }

    @Override
    public void addFilter(LogFilter filter) {
        this.filters.add(filter);
    }

    @Override
    public void removeFilter(LogFilter filter) {
        this.filters.remove(filter);
    }

    @Override
    public List<LogAppender> getAppenders() {
        return new ArrayList<>(appenders);
    }

    @Override
    public List<LogFilter> getFilters() {
        return new ArrayList<>(filters);
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return level;
    }

    /**
     * Gets the calling class name for source information.
     * This is a simplified implementation.
     */

    private String getSourceClassName() {
        try {
            // Get the current stack trace
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            // We expect at least 4 stack frames so index 3 is valid.
            // If the stack is shorter, we can't reliably determine the caller.
            if (stackTrace.length > 3) {
                String className = stackTrace[3].getClassName();
                String methodName = stackTrace[3].getMethodName();
                return className + "." + methodName;
            }

        } catch (Exception e) {
            // ignore exceptions in source detection
        }
        return "UNKNOWN";
    }


}
