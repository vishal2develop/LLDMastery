package Managers;

import ParkingSpot.ParkingSpot;

import java.util.List;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {
    public FourWheelerParkingSpotManager(List<ParkingSpot> parkingSpots) {
        super(parkingSpots);
    }

    @Override
    public double calculateParkingFee() {
        return 20;
    }
}
