import ParkingSpot.ParkingSpot;
import ParkingSpot.TwoWheelerParkingSpot;
import ParkingSpot.FourWheelerParkingSpot;

public class Main {
    public static void main(String[] args) {
        // Create a two-wheeler parking spot
        ParkingSpot twoWheelerSpot = new TwoWheelerParkingSpot(1, 10.0);  // id: 1, price: $10
        // Create a four-wheeler parking spot
        ParkingSpot fourWheelerSpot = new FourWheelerParkingSpot(2, 20.0);  // id: 2, price: $20

        // Park a vehicle in the two-wheeler spot
        System.out.println("Two-Wheeler Spot:");
        twoWheelerSpot.parkVehicle("Bike123");
        System.out.println("Price: $" + twoWheelerSpot.calculatePrice());
        twoWheelerSpot.unparkVehicle();
        System.out.println();


        // Park a vehicle in the four-wheeler spot
        System.out.println("Four-Wheeler Spot:");
        fourWheelerSpot.parkVehicle("Car456");
        System.out.println("Price: $" + fourWheelerSpot.calculatePrice());
        fourWheelerSpot.unparkVehicle();
    }
}