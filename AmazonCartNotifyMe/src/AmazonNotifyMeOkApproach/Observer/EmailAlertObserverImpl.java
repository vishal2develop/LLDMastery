package AmazonNotifyMeOkApproach.Observer;

import AmazonNotifyMeOkApproach.Observer.Interfaces.NotificationAlertObserver;
import AmazonNotifyMeOkApproach.Observable.Interfaces.StocksObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver {

    String emailId;
    StocksObservable stocksObservable;

    public EmailAlertObserverImpl(String emailId, StocksObservable stocksObservable) {
        this.emailId = emailId;
        this.stocksObservable = stocksObservable;
    }

    @Override
    public void update() {
        sendMail(emailId,"Product is in stock hurry");
    }

    public void sendMail(String emailId, String message) {
        System.out.println("Mail sent to: "+emailId);
    }
}
