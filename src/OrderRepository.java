import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public void save(Order order){
        orders.add(order);
    }

    public List<Order> getAllOrders(){
        return orders;
    }
}
