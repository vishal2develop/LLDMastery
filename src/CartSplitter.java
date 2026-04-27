import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartSplitter {
    public List<CartSplit> splitByLineOfBusiness(Cart cart){
        // Group cart items by line of business
        Map<LineOfBusiness,List<CartItem>> groupedItems = cart.getCartItems()
                .stream()
                .collect(Collectors.groupingBy(cartItem -> cartItem.getProduct().getLineOfBusiness()));

        // Create CartSplit objects for each group and return them
        return groupedItems.entrySet().stream().map(entry -> new CartSplit(entry.getKey(),entry.getValue())).toList();

    }
}
