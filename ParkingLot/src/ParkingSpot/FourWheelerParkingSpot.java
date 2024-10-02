package ParkingSpot;

public class FourWheelerParkingSpot extends ParkingSpot{
    public FourWheelerParkingSpot(int id, double price) {
        super(id, price);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
