package AmazonCartNotifyMeBetter.Observer;

import AmazonCartNotifyMeBetter.Observable.Interfaces.Product;
import AmazonCartNotifyMeBetter.Observer.Interfaces.User;
import AmazonCartNotifyMeBetter.Services.NotificationService;
import AmazonCartNotifyMeBetter.Observable.ProductImpl;

/**
 * The ConcreteUser class implements the User interface and
 * defines how a user reacts to a product change (e.g., receiving a notification).
 */
public class UserImpl implements User {
    private String userName;
    private String email;
    private NotificationService notificationService;


    public UserImpl(String userName, String email,NotificationService notificationService) {
        this.userName = userName;
        this.email = email;
        this.notificationService = notificationService;
    }

    @Override
    public void update(Product product) {
        ProductImpl product1 = (ProductImpl) product;
        String message = "Product " + product1.getProductName()
                + " now has stock " + product1.getStock()
                + " and is priced at $" + product1.getPrice();
        notificationService.sendNotification(this,message);
    }

    public String getUsername() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
