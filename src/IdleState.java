public class IdleState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine vendingMachine,Payment payment) {
        vendingMachine.setPayment(payment);
        vendingMachine.setState(new MoneyInsertedState());

        System.out.println("Money inserted!");
    }
    @Override
    public void selectProduct(VendingMachine vendingMachine,String productId) {
        throw new IllegalStateException("Please insert money first!");
    }
    @Override
    public void dispenseProductAndReturnChange(VendingMachine vendingMachine) {
        throw new IllegalStateException("Please insert money first!");
    }
}
