package Managers;

import Enums.VehicleType;
import ParkingSpot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingSpotManager {
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingSpotManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    // Method to find an empty parking spot
    public ParkingSpot findSpace() {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isEmpty()) {
                return spot;  // Return the first available spot
            }
        }
        System.out.println("No available spots.");
        return null;  // Return null if no spots are available
    }

    public void addSpace(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }
    public void removeSpace(ParkingSpot parkingSpot){
        parkingSpots.remove(parkingSpot);
    }

    // Method to park a vehicle in the first available spot
    public void parkVehicle(VehicleType vehicle) {
        ParkingSpot availableSpot = findSpace();
        if (availableSpot != null) {
            availableSpot.parkVehicle(vehicle);
        }
    }

    // Method to unpark a vehicle
    public void unParkVehicle(VehicleType vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isEmpty() && spot.getVehicle().equals(vehicle)) {
                spot.unparkVehicle();
                return;
            }
        }
        System.out.println("Your Vehicle not found in any spot.");
    }

    // Abstract method for pricing logic to be defined by the subclass
    public abstract double calculateParkingFee();
}
