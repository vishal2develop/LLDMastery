package Managers;

import ParkingSpot.ParkingSpot;

import java.util.List;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager {
    public TwoWheelerParkingSpotManager(List<ParkingSpot> parkingSpots) {
        super(parkingSpots);
    }

    @Override
    public double calculateParkingFee() {
        // Custom pricing logic for two-wheeler
        return 10;
    }
}
