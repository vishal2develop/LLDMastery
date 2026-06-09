public class ProductSlot {
    private Product product;
    private int quantity;

    public ProductSlot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    // why synchronized here? - ProductSlot owns quantity
    // ProductSlot should control quantity mutation
    // State + state mutation should live together.
    public synchronized void decrementQuantity() {
        if (quantity <= 0) {
            throw new IllegalStateException("Out of stock!");
        }
        quantity--;
    }
}
