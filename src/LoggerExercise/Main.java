package LoggerExercise;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.enableFileLogging("logs");  // Set the directory for log files
        // Simulating different components logging messages
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService();

        // Log messages from each service
        userService.processUserAction();
        paymentService.processPayment();
        orderService.processOrder();


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
        // Log multiple messages to trigger log rotation
        for (int i = 1; i <= 10; i++) {
            logger.log("Log message number " + i,PaymentService.class.toString());
        }

        // Simulate date change (this can be automated or changed manually during real runs)
        try {
            // Simulate a wait to change date manually or through time manipulation
            Thread.sleep(5000);  // Wait for 5 seconds to simulate a passage of time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Continue logging (would be a new day in reality)
        logger.log("This message should go into a new log file.",PaymentService.class.toString());
        logger.log("Another message for the new day.",PaymentService.class.toString());

    }
}

class OrderService {
    public void processOrder() {
        Logger logger = Logger.getInstance();
        logger.log("Order processed.",OrderService.class.toString());
    }
}
