package LoggerExercise;

public class Main {
    public static void main(String[] args) {
        // Simulating different components logging messages
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService();

        // Log messages from each service
        userService.processUserAction();
        paymentService.processPayment();
        orderService.processOrder();

        // Verify that the same logger instance is used and check the log count
        Logger logger = Logger.getInstance();
        System.out.println("Total log messages: " + logger.getLogCount());  // Should match the number of logged messages


    }
}

class UserService {
    public void processUserAction() {
        Logger logger = Logger.getInstance();
        logger.log("User action processed.",UserService.class.toString());
    }
}

class PaymentService {
    public void processPayment() {
        Logger logger = Logger.getInstance();
        // Enable file logging with a small file size limit for testing (e.g., 100 bytes)
        logger.enableFileLogging("payment_logs.txt",100);
        // Log multiple messages to trigger log rotation
        for (int i = 1; i <= 10; i++) {
            logger.log("Log message number " + i,PaymentService.class.toString());
        }
    }
}

class OrderService {
    public void processOrder() {
        Logger logger = Logger.getInstance();
        logger.log("Order processed.",OrderService.class.toString());
    }
}
