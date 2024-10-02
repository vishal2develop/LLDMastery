package AmazonNotifyMeOkApproach.Observable.Interfaces;

import AmazonNotifyMeOkApproach.Observer.Interfaces.NotificationAlertObserver;

/**
 * Stocks - Items in Amazon (Iphone,Camera etc..)
 *
 */

public interface StocksObservable {
    public void add(NotificationAlertObserver observer);

    public void remove(NotificationAlertObserver observer);

    public void notifySubscribers();

    public void setStockCount(int newStackAddedCount);

    public int getStockCount();


}
