package Factory.ParkingSpotFactory;

import Enums.VehicleType;
import Managers.FourWheelerParkingSpotManager;
import Managers.ParkingSpotManager;
import Managers.TwoWheelerParkingSpotManager;
import ParkingSpot.ParkingSpot;

import java.util.List;

public class ParkingSpotFactory {
    // Factory method to return the appropriate ParkingSpotManager
    public static ParkingSpotManager getParkingSpotManager(VehicleType vehicleType, List<ParkingSpot> parkingSpots){
        if (vehicleType == VehicleType.TWO_WHEELER){
            return new TwoWheelerParkingSpotManager(parkingSpots);
        } else if (vehicleType == VehicleType.FOUR_WHEELER) {
            return new FourWheelerParkingSpotManager(parkingSpots);
        }
        throw new IllegalArgumentException("Invalid vehicle type");
    }
}
