public class DispensingState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine vendingMachine,Payment payment) {
        throw new IllegalStateException("Money already inserted!");
    }
    @Override
    public void selectProduct(VendingMachine vendingMachine,String productId) {
        throw new IllegalStateException(
                "Please complete current transaction first!"
        );
    }
    @Override
    public void dispenseProductAndReturnChange(VendingMachine vendingMachine) {
        Payment payment = vendingMachine.getPayment();
        ProductSlot slot = vendingMachine.getSelectedProductSlot();
        double change = payment.getAmount() - slot.getProduct().getPrice();
        if (change > 0) {
            System.out.println("Change: $" + String.format("%.2f", change));
        }

        // reset payment, product slot and state
        vendingMachine.setPayment(null);
        vendingMachine.setSelectedProductSlot(null);
        vendingMachine.setState(new IdleState());

    }
}
