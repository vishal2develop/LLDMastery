import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cart {
    private String id;
    private List<CartItem> cartItems;

    public Cart(String id,List<CartItem> cartItems){
        this.id = id;
        this.cartItems = cartItems;
    }

    public double getTotalAmount(){
        return 0.0;
    }
    public int getTotalQuantity(){
        return 0;
    }

    // Get the unique line of businesses in the cart.
    public Set<LineOfBusiness> getLineOfBusinesses(){
        return cartItems.stream()
                .map(item -> item.getProduct().getLineOfBusiness())
                .collect(Collectors.toSet());
    }

    public LineOfBusiness getLineOfBusiness(){
        Set<LineOfBusiness> lineOfBusinesses = getLineOfBusinesses();

        if(lineOfBusinesses.size()!=1){
            throw new IllegalStateException("Cart must contain only one line of business");
        }
        return lineOfBusinesses.iterator().next();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
