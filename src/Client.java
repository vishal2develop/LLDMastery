import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

    public static void main(String[] args) throws Exception {
        testSuccessfulPurchaseFlow();

        System.out.println("\nNegative test cases:--------------------------");
        testInvalidStateFlows();

        System.out.println("\nConcurrency test:-----------------------------");
        testConcurrentPurchaseOfLastItem();
    }

    private static void testSuccessfulPurchaseFlow() {
        Inventory inventory = createInventory(2);

        VendingMachine vendingMachine =
                new VendingMachine("VM-1", inventory);

        vendingMachine.insertMoney(
                new Payment(
                        PaymentStrategyFactory.getPaymentStrategy(PaymentModes.CASH),
                        1300
                )
        );

        vendingMachine.selectProduct("2");
        vendingMachine.dispenseProductAndReturnChange();
    }

    private static void testInvalidStateFlows() {
        Inventory inventory = createInventory(2);

        VendingMachine vendingMachine =
                new VendingMachine("VM-2", inventory);

        // Selecting product before inserting money should fail.
        try {
            vendingMachine.selectProduct("2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        vendingMachine.insertMoney(
                new Payment(
                        PaymentStrategyFactory.getPaymentStrategy(PaymentModes.UPI),
                        1300
                )
        );

        vendingMachine.selectProduct("2");

        // Selecting another product while current transaction is in progress should fail.
        try {
            vendingMachine.selectProduct("1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testConcurrentPurchaseOfLastItem() throws Exception {
        Inventory sharedInventory = createInventory(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            int userId = i;

            futures.add(executor.submit(() -> {
                try {
                    VendingMachine vendingMachine =
                            new VendingMachine("VM-" + userId, sharedInventory);

                    vendingMachine.insertMoney(
                            new Payment(
                                    PaymentStrategyFactory.getPaymentStrategy(PaymentModes.CASH),
                                    1300
                            )
                    );

                    vendingMachine.selectProduct("2");
                    vendingMachine.dispenseProductAndReturnChange();

                    return "User " + userId + " purchase successful";
                } catch (Exception e) {
                    return "User " + userId + " purchase failed: " + e.getMessage();
                }
            }));
        }

        executor.shutdown();

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        ProductSlot macbookSlot = sharedInventory.getProductSlot("2");
        System.out.println("Remaining Macbook quantity: " + macbookSlot.getQuantity());
    }

    private static Inventory createInventory(int macbookQuantity) {
        Inventory inventory = new Inventory();

        Product coffee =
                new Product("1", "Coffee", 2.00, "Beverages");

        Product macbook =
                new Product("2", "Macbook Pro", 1299.99, "Electronics");

        inventory.addProductSlot(new ProductSlot(coffee, 10));
        inventory.addProductSlot(new ProductSlot(macbook, macbookQuantity));

        return inventory;
    }
}