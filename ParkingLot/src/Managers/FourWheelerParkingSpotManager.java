package Managers;

import ParkingSpot.ParkingSpot;

import java.util.List;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {
    public FourWheelerParkingSpotManager(List<ParkingSpot> parkingSpots) {
        super(parkingSpots);
    }

    // Implementation of findSpace for four-wheelers
    @Override
    public ParkingSpot findSpace() {
        // Iterate through the parking spots and find the first available four-wheeler spot
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isEmpty()) {
                return spot;  // Return the first available spot
            }
        }
        return null;  // No spots available
    }


    @Override
    public double calculateParkingFee() {
        return 20;
    }
}
