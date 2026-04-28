import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) throws Exception {

        // Step 1: Setup product + inventory
        Product iphone = new Product("P1", "iPhone", "Flash sale iPhone", 70000.0);

        Inventory inventory = new Inventory(iphone, 5);

        Map<String, Inventory> inventoryMap = Map.of(
                iphone.getProductId(), inventory
        );

        // Step 2: Create repository + service
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(inventoryMap, orderRepository);

        // Step 3: Create queue + worker
        FlashSaleQueue queue = new FlashSaleQueue();

        Thread worker = new Thread(new OrderWorker(queue, orderService));
        worker.start();

        // Step 4: Create users
        List<User> users = List.of(
                new User("U1", "User 1"),
                new User("U2", "User 2"),
                new User("U3", "User 3"),
                new User("U4", "User 4"),
                new User("U5", "User 5"),
                new User("U6", "User 6"),
                new User("U7", "User 7"),
                new User("U8", "User 8"),
                new User("U9", "User 9"),
                new User("U10", "User 10")
        );

        // Step 5: Submit requests to queue
        for (User user : users) {
            queue.submit(new PurchaseRequest(user, iphone));
        }

        // Small wait only for demo so worker can process requests
        Thread.sleep(2000);

        System.out.println("Successful Orders: " + orderRepository.getAllOrders().size());
        System.out.println("Remaining stock: " + inventory.getAvailableQuantity());

        worker.interrupt();
    }
}