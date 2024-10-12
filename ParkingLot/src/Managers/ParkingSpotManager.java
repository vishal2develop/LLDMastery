package Managers;

import Enums.VehicleType;
import ParkingSpot.ParkingSpot;
import Ticket.Ticket;
import Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingSpotManager {
    protected List<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingSpotManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    // Abstract method to find an available parking spot
    public abstract ParkingSpot findSpace();

    // Method to book the spot by parking the vehicle in it
    public void parkVehicle(Vehicle vehicle) {
        ParkingSpot availableSpot = findSpace();
        if (availableSpot != null) {
            availableSpot.parkVehicle(vehicle.getLicensePlate());  // Park the vehicle in the found spot
            System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked in Spot ID: " + availableSpot.getId());
        } else {
            System.out.println("No available spot for vehicle: " + vehicle.getLicensePlate());
        }
    }


    public void addSpace(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }
    public void removeSpace(ParkingSpot parkingSpot){
        parkingSpots.remove(parkingSpot);
    }

    // Method to park a vehicle in the first available spot
//    public void parkVehicle(Vehicle vehicle) {
//        ParkingSpot availableSpot = findSpace();
//        if (availableSpot != null) {
//            availableSpot.parkVehicle(vehicle);
//            System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked in spot ID: " + availableSpot.getId());
//        }
//        else{
//            System.out.println("No available parking spots for " + vehicle.getLicensePlate());
//        }
//    }

    // Method to unpark the vehicle using the ticket
    public void unparkVehicle(Ticket ticket) {
        ParkingSpot spot = ticket.getParkingSpot();
        if (spot != null && !spot.isEmpty()) {
            spot.unparkVehicle();
            System.out.println("Vehicle with license plate " + ticket.getVehicle().getLicensePlate() + " has been unparked from Spot ID: " + spot.getId());
        } else {
            System.out.println("Spot is already empty or invalid for vehicle: " + ticket.getVehicle().getLicensePlate());
        }
    }

    // Abstract method for pricing logic to be defined by the subclass
    public abstract double calculateParkingFee();
}
