public class CartItem {
    private String id;
    private Product product;
    private int quantity;

    public CartItem(String id, Product product, int quantity){
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice(){
        return 0.0;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
