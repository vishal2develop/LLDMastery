package Strategies;

import Interfaces.ParkingStrategy;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public void findParkingSpot() {
        System.out.println("Finding parking spot");
    }
}
