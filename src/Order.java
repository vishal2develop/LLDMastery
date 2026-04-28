import java.util.List;

public class Order {
    private String orderId;
    private User user;
    private Product product;
    private OrderStatus status;

    public Order(String orderId, User user, Product product, OrderStatus status) {
        this.orderId = orderId;
        this.user = user;
        this.product = product;
        this.status = status;
    }

    //getters

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public OrderStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + user.getUserId() + '\'' +
                ", productId='" + product.getProductId() + '\'' +
                ", status=" + status +
                '}';
    }
}
