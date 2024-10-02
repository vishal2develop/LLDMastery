package AmazonNotifyMeOkApproach.Observer;

import AmazonNotifyMeOkApproach.Observer.Interfaces.NotificationAlertObserver;
import AmazonNotifyMeOkApproach.Observable.Interfaces.StocksObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver {
    String userName;
    StocksObservable stocksObservable;

    public MobileAlertObserverImpl(String userName, StocksObservable stocksObservable) {
        this.userName = userName;
        this.stocksObservable = stocksObservable;
    }



    @Override
    public void update() {
        sendSMS(userName,"Product is in stock hurry!!");

    }

    public void sendSMS(String userName,String message) {
        System.out.println("SMS sent to: "+userName);
    }
}
