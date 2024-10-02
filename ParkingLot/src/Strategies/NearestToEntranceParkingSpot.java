package Strategies;

import Interfaces.ParkingStrategy;

public class NearestToEntranceParkingSpot implements ParkingStrategy {
    @Override
    public void findParkingSpot() {
        System.out.println("Finding Nearest To Entrance Parking Spot");
    }
}
