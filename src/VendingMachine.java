public class VendingMachine {
    private String id;
    private Inventory inventory;
    private Payment payment;
    private VendingMachineState state;
    private ProductSlot selectedProductSlot;

    public VendingMachine(String id, Inventory inventory) {
        this.id = id;
        this.inventory = inventory;
        this.state = new IdleState();
    }

    public void insertMoney(Payment payment) {
        state.insertMoney(this, payment);
    }

    public void selectProduct(String productId) {
        state.selectProduct(this, productId);
    }

    public void dispenseProductAndReturnChange() {
        state.dispenseProductAndReturnChange(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Payment getPayment() { return payment; }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public VendingMachineState getState() {
        return state;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public ProductSlot getSelectedProductSlot() {
        return selectedProductSlot;
    }

    public void setSelectedProductSlot(ProductSlot selectedProductSlot) {
        this.selectedProductSlot = selectedProductSlot;
    }
}
