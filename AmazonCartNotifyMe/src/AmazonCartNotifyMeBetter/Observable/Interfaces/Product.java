package AmazonCartNotifyMeBetter.Observable.Interfaces;

import AmazonCartNotifyMeBetter.Observer.Interfaces.User;

/**
 * The Product interface defines methods to add and remove observers (users) and
 * notify them of any state changes.
 */
public interface Product {
    void addObserver(User observer);
    void removeObserver(User observer);
    void notifyObservers();
}
