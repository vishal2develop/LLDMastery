package ParkingSpot;

public class TwoWheelerParkingSpot extends ParkingSpot {

    public TwoWheelerParkingSpot(int id, double price){
        super(id, price);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
