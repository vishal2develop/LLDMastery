package AmazonCartNotifyMeBetter.Observer.Interfaces;

import AmazonCartNotifyMeBetter.Observable.Interfaces.Product;

/**
 * The ConcreteUser class implements the User interface and
 * defines how a user reacts to a product change (e.g., receiving a notification).
 */
public interface User {
    void update(Product product);  // Called when product state changes
}
