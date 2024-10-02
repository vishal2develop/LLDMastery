package Strategies;

import Interfaces.ParkingStrategy;

public class NearestToEntranceAndElevator implements ParkingStrategy {
    @Override
    public void findParkingSpot() {
        System.out.println("Finding Nearest To Entrance & Elevator Parking Spot");
    }
}
