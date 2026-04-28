public class PurchaseRequest {
    private User user;
    private Product product;

    public PurchaseRequest(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }
}
