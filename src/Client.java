import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws Exception {
        // Step 1 Setup product + inventory
        Product iphone = new Product("P1", "iPhone", "Flash sale iPhone", 70000.0);

        Inventory inventory = new Inventory(iphone, 5); // only 5 units

        Map<String, Inventory> inventoryMap = Map.of(
                iphone.getProductId(), inventory
        );

        // Step 2: Create repository + service
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(inventoryMap, orderRepository);

        // Step 3: Create users

        List<User> users = List.of(
                new User("U1","User 1"),
                new User("U2","User 2"),
                new User("U3","User 3"),
                new User("U4","User 4"),
                new User("U5","User 5"),
                new User("U6","User 6"),
                new User("U7","User 7"),
                new User("U8","User 8"),
                new User("U9","User 9"),
                new User("U10","User 10")
        );

        // Step 4: Run concurrent orders
        // Create 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // Store Orders in a list
        List<Future<Order>> futures = new ArrayList<>();
        for (User user : users) {
            futures.add(executor.submit(() -> orderService.placeOrder(user, iphone)));
        }

        // Release threads
        executor.shutdown();

        // Collect results
        int success = 0;
        int failed = 0;

        for (Future<Order> future : futures) {
            Order order = future.get();

            if (order.getStatus() == OrderStatus.CREATED) {
                success++;
            } else {
                failed++;
            }

            System.out.println(order);
        }

            System.out.println("Success: " + success);
            System.out.println("Failed: " + failed);
            System.out.println("Remaining stock: " + inventory.getAvailableQuantity());
        }




}
