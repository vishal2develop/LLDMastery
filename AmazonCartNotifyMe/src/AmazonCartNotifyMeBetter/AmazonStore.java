package AmazonCartNotifyMeBetter;

import AmazonCartNotifyMeBetter.Observer.Interfaces.User;
import AmazonCartNotifyMeBetter.Observable.ProductImpl;
import AmazonCartNotifyMeBetter.Observer.UserImpl;
import AmazonCartNotifyMeBetter.Services.NotificationService;

public class AmazonStore {
    public static void main(String[] args) {
        // Create the NotificationService
        NotificationService notificationService = new NotificationService();
        // Create a product
        ProductImpl product = new ProductImpl("iPhone", 0, 999.99);

        // Create users who want to be notified
        User user1 = new UserImpl("Alice", "alice@example.com",notificationService);
        User user2 = new UserImpl("Bob", "bob@example.com",notificationService);

        // Users subscribe to product notifications
        product.addObserver(user1);
        product.addObserver(user2);

        // Product's state changes (restock)
        product.setStock(100);  // This triggers notification to all subscribed users

        // Product's state changes (price drop)
        product.setPrice(899.99);  // This triggers another notification to all subscribed users

        // Remove a user from the notification list
        product.removeObserver(user1);

        // Only Bob gets this notification now
        product.setPrice(799.99);
    }
}
