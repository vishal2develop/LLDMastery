import Enums.VehicleType;
import Managers.FourWheelerParkingSpotManager;
import Managers.ParkingSpotManager;
import Managers.TwoWheelerParkingSpotManager;
import ParkingSpot.ParkingSpot;
import ParkingSpot.TwoWheelerParkingSpot;
import ParkingSpot.FourWheelerParkingSpot;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create parking spots for two-wheelers
        List<ParkingSpot> twoWheelerSpots = new ArrayList<>();
        twoWheelerSpots.add(new TwoWheelerParkingSpot(1, 10.0));
        twoWheelerSpots.add(new TwoWheelerParkingSpot(2, 10.0));

        // Create parking spots for four-wheelers
        List<ParkingSpot> fourWheelerSpots = new ArrayList<>();
        fourWheelerSpots.add(new FourWheelerParkingSpot(1, 20.0));
        fourWheelerSpots.add(new FourWheelerParkingSpot(2, 20.0));

        // Create parking managers
        ParkingSpotManager twoWheelerManager = new TwoWheelerParkingSpotManager(twoWheelerSpots);
        ParkingSpotManager fourWheelerManager = new FourWheelerParkingSpotManager(fourWheelerSpots);

        // Park a two-wheeler
        System.out.println("Two-Wheeler Parking:");
        twoWheelerManager.parkVehicle(VehicleType.TWO_WHEELER);
        System.out.println("Parking Fee: $" + twoWheelerManager.calculateParkingFee());

        // Park a four-wheeler
        System.out.println("\nFour-Wheeler Parking:");
        fourWheelerManager.parkVehicle(VehicleType.FOUR_WHEELER);
        System.out.println("Parking Fee: $" + fourWheelerManager.calculateParkingFee());

        // Unpark the vehicles
        System.out.println("\nUnparking Vehicles:");
        twoWheelerManager.unParkVehicle(VehicleType.TWO_WHEELER);
        fourWheelerManager.unParkVehicle(VehicleType.FOUR_WHEELER);
    }
}