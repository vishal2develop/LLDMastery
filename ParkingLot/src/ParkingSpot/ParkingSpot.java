package ParkingSpot;

import Enums.VehicleType;
import Vehicle.Vehicle;

public abstract class ParkingSpot {
    private int id;
    private boolean isEmpty;
    private double price;
    private Vehicle vehicle;
    private String vehicleLicensePlate;  // Stores the license plate of the parked vehicle

    public ParkingSpot(int id, double price) {
        this.id = id;
        this.price = price;
        this.isEmpty = true; // Initially, the spot is empty
        this.vehicleLicensePlate = null;  // No vehicle is parked initially
    }

    public void parkVehicle(String vehicleLicensePlate) {
        if (isEmpty) {
            this.vehicleLicensePlate = vehicleLicensePlate;
            this.isEmpty = false;
            System.out.println("Vehicle parked: " + vehicleLicensePlate);
        }
        else {
            System.out.println("Spot is already occupied.");
        }
    }
    public void unparkVehicle(){
        if(!isEmpty){
            System.out.println("Vehicle unparked: " + vehicleLicensePlate);
            this.vehicleLicensePlate = null;
            this.isEmpty = true;
        }
        else {
            System.out.println("Spot is already empty.");
        }
    }

    public void genrateTicket(){

    }

    // Abstract method for calculating price
    public abstract double calculatePrice();

    // Getters

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public double getPrice() {
        return price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
