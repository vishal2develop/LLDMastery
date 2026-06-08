import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ProductSlot> slots = new ArrayList<>();

    public void addProductSlot(ProductSlot slot) {
        slots.add(slot);
    }

    public ProductSlot getProductSlot(String productId) {
        return slots
                .stream()
                .filter(slot ->
                        slot.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

}
