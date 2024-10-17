## **LoggerExercise**

### **Project Overview**
This project demonstrates the implementation of a **Singleton Logger** class that logs messages from different services in a simulated application (e.g., `UserService`, `PaymentService`, `OrderService`). The logger ensures that only **one instance** of the `Logger` is created, providing **global access** to log messages across different parts of the application.

### **Features**
- **Singleton Design Pattern**: Ensures that only one instance of the logger is used across the entire application.
- **File Logging**: Logs can be written to a file, with optional rotation based on **date** (daily rotation).
- **Console Logging**: If file logging is not enabled, logs will be printed to the console.
- **Date-Based Log Rotation**: Automatically creates a new log file each day, ensuring that logs do not accumulate indefinitely in a single file.
- **Log Count Tracking**: Keeps track of the total number of log messages recorded.

---

### **Project Structure**
```
LoggerExercise/
├── LoggerExercise/
│   ├── Main.java               # Client code that uses the Logger across different services
│   └── Logger.java             # Singleton Logger class with file rotation logic
├── logs/                       # Directory where log files are generated (created during runtime)
├── README.md                   # This file
```

---

### **Usage**

#### **1. Enabling File Logging with Date-Based Rotation**

To enable **file logging** with **date-based rotation**, you can specify the directory where the log files will be stored. The logger will create a new log file each day (e.g., `logs_YYYY-MM-DD.txt`).

```java
// Enable file logging with date-based log rotation
Logger logger = Logger.getInstance();
logger.enableFileLogging("logs");  // Logs will be stored in the 'logs' directory
```

#### **2. Logging Messages**

Each service (e.g., `UserService`, `PaymentService`) uses the **singleton logger** to log messages. The logger automatically checks whether to rotate the log file when a new day starts.

```java
// Example of logging a message in a service
Logger logger = Logger.getInstance();
logger.log("User action processed.", UserService.class.toString());  // Logs with the service name
```

#### **3. Checking Log Count**

The total number of log messages can be tracked using the `getLogCount()` method of the `Logger`.

```java
// Check the total number of log messages
int logCount = logger.getLogCount();
System.out.println("Total log messages: " + logCount);
```

---

### **Example Output**

#### **Console Output**:
```
Logging to file enabled: logs/logs_2024-10-12.txt
Total log messages: 14
```

#### **File Output (logs/logs_2024-10-12.txt)**:
```
[2024-10-12 14:00:01] User action processed. - UserService
[2024-10-12 14:00:02] Log message number 1 - PaymentService
...
```

---

### **Classes**

#### **1. `Logger` Class**
The `Logger` is implemented as a **Singleton** to ensure there is only one instance of the logger. It handles both **console logging** and **file logging**. The logger rotates the log file based on the **current date** (new log file each day).

**Key Methods**:
- `getInstance()`: Returns the singleton instance of the logger.
- `enableFileLogging(String fileDirectory)`: Enables file logging and sets the directory where log files will be stored. It supports daily log rotation.
- `log(String message, String serviceName)`: Logs a message with a timestamp. The message is written to either the console or a log file, depending on the configuration.
- `getLogCount()`: Returns the total number of log messages recorded.

#### **2. Services (`UserService`, `PaymentService`, `OrderService`)**
These services simulate different components of the system, each logging messages to the **Logger**.

---

### **Log Rotation**

**Date-Based Log Rotation**:
- The logger creates a new log file **each day**, with the filename format `logs_YYYY-MM-DD.txt`.
- The `rotateLogFileIfNeeded()` method in the `Logger` class checks if the date has changed, and if so, it rotates the log file and starts logging to a new file.

---

### **How to Run the Project**

1. **Set Up the Project**: Clone the repository or create a new project directory for `LoggerExercise`.
2. **Compile and Run**: Compile and run the `Main` class.
3. **Check Logs**: If file logging is enabled, check the `logs` directory for the generated log files.

---

### **Customization**

- **Log Rotation Based on Size**: You can extend the `Logger` class to rotate logs based on file size by adding a `maxFileSize` parameter and checking the file size before logging.
- **Log Rotation Based on Time**: You can modify the logger to rotate logs based on **hourly intervals** by checking the current hour instead of the date.

---

### **License**
This project is open for educational purposes and can be modified as needed.

---

Feel free to modify or enhance the `README.md` based on your project structure and any additional functionality you plan to add! Let me know if you need further adjustments.