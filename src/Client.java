import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        Inventory inventory = new Inventory();

    //     create few products
        Product coffee = new Product("1", "Coffee", 2.00, "Beverages");
        Product macbook  = new Product("2", "Macbook Pro", 1299.99, "Electronics");

        inventory.addProductSlot(new ProductSlot(coffee, 10));
        inventory.addProductSlot(new ProductSlot(macbook, 2));

        VendingMachine vendingMachine = new VendingMachine("1", inventory);

        vendingMachine.insertMoney(new Payment(PaymentModes.CASH,1300));
        vendingMachine.selectProduct("2");
        vendingMachine.dispenseProductAndReturnChange();

        System.out.println("Negative test case:--------------------------");

        // Negative test case
        try {
            vendingMachine.selectProduct("2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Negative test case:--------------------------");

        vendingMachine.insertMoney(new Payment(PaymentModes.CASH, 1300));

        vendingMachine.selectProduct("2");

        try {
            vendingMachine.selectProduct("1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
