package LoggerExercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private int logCount=0;

    private boolean logToFile = false;  // Flag to toggle between console and file logging
    private String logFilePath;  // Path to the log file
    private long maxFileSize = 1024 * 1024;  // Maximum file size (1MB default)
    private int logFileIndex = 1;  // To track rotated log files

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
    public void enableFileLogging(String filePath,long maxFileSize) {
        this.logToFile = true;
        this.logFilePath = filePath;
        this.maxFileSize = maxFileSize;
        System.out.println("Logging to file enabled: " + filePath);
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

    // Method to handle log rotation based on file size
    private void rotateLogFileIfNeeded() {
        File logFile = new File(logFilePath);

        // Check if file size exceeds the limit
        if (logFile.exists() && logFile.length() >= maxFileSize) {
            // Rename the current log file with an index (e.g., logs_1.txt, logs_2.txt)
            File rotatedFile = new File("logs_" + logFileIndex++ + ".txt");
            if (logFile.renameTo(rotatedFile)) {
                System.out.println("Log file rotated: " + rotatedFile.getName());
            } else {
                System.err.println("Failed to rotate log file.");
            }

            // Create a new log file (overwrite the old one)
            try {
                new FileWriter(logFilePath, false).close();  // Create an empty file
            } catch (IOException e) {
                System.err.println("Error creating new log file: " + e.getMessage());
            }
        }
    }

    // Get the number of log messages
    public int getLogCount() {
        return logCount;
    }
}
