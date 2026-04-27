import java.util.List;

public class CartSplit implements PayableContext{
    private LineOfBusiness lineOfBusiness;
    List<CartItem> cartItems;

    public CartSplit(LineOfBusiness lineOfBusiness, List<CartItem> cartItems){
        this.lineOfBusiness = lineOfBusiness;
        this.cartItems = cartItems;
    }

    @Override
    public LineOfBusiness getLineOfBusiness() {
        return lineOfBusiness;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public double getTotalAmount() {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
