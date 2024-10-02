package AmazonNotifyMeOkApproach.Observable;

import AmazonNotifyMeOkApproach.Observer.Interfaces.NotificationAlertObserver;
import AmazonNotifyMeOkApproach.Observable.Interfaces.StocksObservable;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImpl implements StocksObservable {
    public List<NotificationAlertObserver> observerList = new ArrayList<NotificationAlertObserver>();
    public int stockCount = 0;
    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (NotificationAlertObserver observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void setStockCount(int newStackAddedCount) {
        if(stockCount== 0) {
            notifySubscribers();
        }
        stockCount+=newStackAddedCount;
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }
}
