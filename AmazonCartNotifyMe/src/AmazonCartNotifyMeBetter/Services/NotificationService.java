package AmazonCartNotifyMeBetter.Services;

import AmazonCartNotifyMeBetter.Observer.Interfaces.User;
import AmazonCartNotifyMeBetter.Observer.UserImpl;

public class NotificationService {
    public void sendNotification(User user, String message) {
        System.out.println("Sending notification to " + ((UserImpl) user).getEmail() + ": " + message);
    }
}
