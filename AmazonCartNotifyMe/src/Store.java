import AmazonNotifyMeOkApproach.Observable.IphoneObservableImpl;
import AmazonNotifyMeOkApproach.Observer.EmailAlertObserverImpl;
import AmazonNotifyMeOkApproach.Observer.Interfaces.NotificationAlertObserver;
import AmazonNotifyMeOkApproach.Observable.Interfaces.StocksObservable;
import AmazonNotifyMeOkApproach.Observer.MobileAlertObserverImpl;

public class Store {
    public static void main(String[] args) {
        StocksObservable iphoneStockObservable = new IphoneObservableImpl();
        // observer1 & observer2 clicked on Notify Me via Email.
        // observer3 clicked on Notify Me via Mobile Notification
        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("test1@abc.com", iphoneStockObservable);
        NotificationAlertObserver observer2 = new EmailAlertObserverImpl("test2@abc.com", iphoneStockObservable);
        NotificationAlertObserver observer3 = new MobileAlertObserverImpl("test3", iphoneStockObservable);

        // Registering Observers/subscribers/customers who clicked on notify me
        iphoneStockObservable.add(observer1);
        iphoneStockObservable.add(observer2);
        iphoneStockObservable.add(observer3);

        // stock becomes available -> observers are notified
        iphoneStockObservable.setStockCount(10);

    }
}
