import AbstractClass.LogProcessor;
import ConcreteClasses.DebugLogger;
import ConcreteClasses.ErrorLogger;
import ConcreteClasses.InfoLogger;

public class Main {
    public static void main(String[] args) {
        // Create individual loggers
        LogProcessor infoLogger = new InfoLogger();
        LogProcessor debugLogger = new DebugLogger();
        LogProcessor errorLogger = new ErrorLogger();

        // create chain: Info>Debug>Error
        infoLogger.setNextHandler(debugLogger);
        debugLogger.setNextHandler(errorLogger);

        // Log messages
        infoLogger.log("System is starting", "INFO");   // Handled by InfoLogger
        infoLogger.log("Debugging application", "DEBUG"); // Handled by DebugLogger
        infoLogger.log("Critical error occurred!", "ERROR"); // Handled by ErrorLogger
        infoLogger.log("Unknown log level", "TRACE"); // Not handled, default message
    }
}