import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {

    // Maps each productId to its corresponding Inventory
    private Map<String, Inventory> inventoryMap;

    // Stores all successfully created orders
    private OrderRepository orderRepository;

    // Keeps track of which user has already successfully purchased which product
    // Format: "userId:productId"
    // This helps prevent duplicate purchases by the same user
    private Set<String> successfulPurchases = ConcurrentHashMap.newKeySet();

    public OrderService(Map<String, Inventory> inventoryMap, OrderRepository orderRepository) {
        this.inventoryMap = inventoryMap;
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(User user, Product product){

        // Create a unique key for this user-product combination
        String purchaseKey = purchaseKey(user, product);

        // STEP 1: Check if this user has already purchased this product
        // add() returns false if the key already exists
        // This operation is thread-safe and atomic - uses ConcurrentHashMap
        if (!successfulPurchases.add(purchaseKey)) {
            // User already bought this product → reject duplicate order
            return new Order(generateOrderId(), user, product, OrderStatus.FAILED);
        }

        // STEP 2: Get inventory for this product
        Inventory inventory = inventoryMap.get(product.getProductId());

        // If inventory does not exist → fail the order
        if (inventory == null) {
            // Important: remove key since the purchase did not succeed
            successfulPurchases.remove(purchaseKey);

            return new Order(generateOrderId(), user, product, OrderStatus.FAILED);
        }

        // STEP 3: Try to reserve stock (atomic operation inside Inventory)
        if (inventory.reserve()) {

            // Stock successfully reserved → create order
            String orderId = generateOrderId();

            Order order = new Order(orderId, user, product, OrderStatus.CREATED);

            // Save a successful order
            orderRepository.save(order);

            return order;
        }

        // STEP 4: Stock not available (sold out)

        // Important: release the key since purchase failed
        // Otherwise user will be blocked incorrectly in future attempts
        successfulPurchases.remove(purchaseKey);

        return new Order(generateOrderId(), user, product, OrderStatus.FAILED);
    }

    // Generates a simple unique order ID
    private String generateOrderId(){
        return String.valueOf(System.nanoTime());
    }

    // Creates a unique identifier for user-product combination
    private String purchaseKey(User user, Product product){
        return user.getUserId() + ":" + product.getProductId();
    }
}