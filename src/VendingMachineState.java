public interface VendingMachineState {
    void insertMoney(VendingMachine vendingMachine, Payment payment);
    void selectProduct(VendingMachine vendingMachine,String productId);
    void dispenseProductAndReturnChange(VendingMachine vendingMachine);
}
