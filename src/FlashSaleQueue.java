import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FlashSaleQueue {
    // Queue to store flash sale requests
    private BlockingQueue<PurchaseRequest> queue = new LinkedBlockingQueue<>();

    // Methods to add and remove items from the queue
    // offer - returns true if the item was added, false otherwise
    public void submit(PurchaseRequest request){
        queue.offer(request);
    }

    // take - blocks until an item is available
    // poll - returns null if no item is available
    public PurchaseRequest take() throws InterruptedException{
        return queue.take();
    }

}
