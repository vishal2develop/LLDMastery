public class MoneyInsertedState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine vendingMachine,Payment payment) {
        throw new IllegalStateException("Money already inserted!");
    }
    @Override
    public void selectProduct(VendingMachine vendingMachine,String productId) {
        Inventory inventory = vendingMachine.getInventory();
        Payment payment = vendingMachine.getPayment();

        ProductSlot slot = inventory.getProductSlot(productId);
        if (payment.getAmount() < slot.getProduct().getPrice()) {
            throw new IllegalStateException("Insufficient funds!");
        }
        // reserve stock first
        slot.decrementQuantity();
        // process payment only after stock is reserved
        payment.getPaymentStrategy().processPayment(payment.getAmount());
        System.out.println(
                "Dispensing " + slot.getProduct().getName()
        );

        vendingMachine.setSelectedProductSlot(slot);
        vendingMachine.setState(new DispensingState());
    }

    @Override
    public void dispenseProductAndReturnChange(VendingMachine vendingMachine) {
        throw new IllegalStateException("Please select a product first!");
    }
}
