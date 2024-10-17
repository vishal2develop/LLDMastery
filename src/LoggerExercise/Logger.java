package LoggerExercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private int logCount=0;

    private boolean logToFile = false;  // Flag to toggle between console and file logging
    private String logFilePath;  // Path to the log file
    private LocalDate currentLogDate;  // Tracks the current date for daily log rotation


    private Logger() {
        System.out.println("Creating Logger instance");
    }

    // Bill Pugh Singleton
    private static class LoggerHelper{
        private static final Logger instance = new Logger();
    }

    public static Logger getInstance() {
       return LoggerHelper.instance;
    }

    // Configure logging to file
    public void enableFileLogging(String fileDirectory) {
        this.logToFile = true;
        this.currentLogDate = LocalDate.now();  // Initialize the log date
        this.logFilePath = generateLogFileName(fileDirectory, currentLogDate);  // Set the log file for today
        System.out.println("Logging to file enabled: " + logFilePath);
    }

    public void log(String message,String className) {
        logCount++;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = className+"[" + timestamp + "] " + message;
        if (logToFile && logFilePath != null) {
            // Write log message to file
            rotateLogFileIfNeeded();
            writeLogToFile(formattedMessage);
        } else {
            // Log to console by default
            System.out.println(formattedMessage);
        }
    }

    // Helper method to write logs to the file
    private void writeLogToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {  // true = append mode
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Method to handle log rotation based on date
    private void rotateLogFileIfNeeded() {
        LocalDate today = LocalDate.now();
        if (!today.equals(currentLogDate)) {  // If the date has changed, rotate the log
            System.out.println("Date has changed. Rotating log file.");
            currentLogDate = today;
            logFilePath = generateLogFileName("logs", currentLogDate);  // Create a new log file for the new day
        }
    }

    // Generate a new log file name based on the current date
    private String generateLogFileName(String directory, LocalDate date) {
        return directory + File.separator + "logs_" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".txt";
    }

    // Get the number of log messages
    public int getLogCount() {
        return logCount;
    }
}
