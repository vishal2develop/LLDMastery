package AmazonCartNotifyMeBetter.Observable;

import AmazonCartNotifyMeBetter.Observable.Interfaces.Product;
import AmazonCartNotifyMeBetter.Observer.Interfaces.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The ProductImpl class implements the Product interface and stores information
 * like stock and price. It keeps track of users who are observing this product for changes.
 */
public class ProductImpl implements Product {
    private String productName;
    private int stock;
    private double price;
    private List<User> observers = new ArrayList<>();

    public ProductImpl(String productName, int stock, double price) {
        this.productName = productName;
        this.stock = stock;
        this.price = price;
    }

    @Override
    public void addObserver(User observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(User observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (User observer : observers) {
            observer.update(this); // Notify each observer of the product change
        }
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyObservers();  // Notify all users of the stock change
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();  // Notify all users of the price change
    }

    public String getProductName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

}
