public class Inventory {
    private Product product;
    private int availableQuantity;

    public Inventory(Product product, int availableQuantity) {
        this.product = product;
        this.availableQuantity = availableQuantity;
    }

    // Atomically Check & decrement available quantity using synchronized
    public synchronized boolean reserve(){
        if(availableQuantity <= 0){
            return false;
        }
        availableQuantity--;
        return true;
    }

    // Optional: To undo the reservation, increment the available quantity. Useful in cases like: Order Failure, Cancellation, Payment failure etc..
    public synchronized void release(){
        availableQuantity++;
    }

    // getters
    public Product getProduct() {
        return product;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

}
