public class ProductSlot {
    private Product product;
    private int quantity;

    public ProductSlot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    public void decrementQuantity() {
        if (quantity <= 0) {
            throw new IllegalStateException("Out of stock!");
        }
        quantity--;
    }
}
