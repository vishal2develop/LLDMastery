import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ProductSlot {
    private Product product;
    private int quantity;

    // Create a fair reentrant lock
    // true means that the lock will be acquired in the order in which it was acquired
    // false means that the lock will be acquired in a random order
    private final ReentrantLock lock = new ReentrantLock(true);

    public ProductSlot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public void decrementQuantity() {
        boolean lockAcquired = false;
        try {
            // Acquire the lock for 2 seconds
            lockAcquired = lock.tryLock(2, TimeUnit.SECONDS);
            if (!lockAcquired) {
                throw new IllegalStateException("Product slot is busy. Please try again.");
            }

            if (quantity <= 0) {
                throw new IllegalStateException("Out of stock!");
            }
            quantity--;

        }
        catch (InterruptedException e) {
            // interrupt the thread
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Purchase interrupted", e);
        }
        finally {
            // release the lock
            if (lockAcquired) {
                lock.unlock();
            }
        }

    }
}
