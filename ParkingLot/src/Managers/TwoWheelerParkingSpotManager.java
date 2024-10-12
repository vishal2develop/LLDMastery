package Managers;

import ParkingSpot.ParkingSpot;

import java.util.List;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager {
    public TwoWheelerParkingSpotManager(List<ParkingSpot> parkingSpots) {
        super(parkingSpots);
    }

    // Implementation of findSpace for two-wheelers
    @Override
    public ParkingSpot findSpace() {
        // Iterate through the parking spots and find the first available two-wheeler spot
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isEmpty()) {
                return spot;  // Return the first available spot
            }
        }
        return null;  // No spots available
    }

    @Override
    public double calculateParkingFee() {
        // Custom pricing logic for two-wheeler
        return 10;
    }
}
