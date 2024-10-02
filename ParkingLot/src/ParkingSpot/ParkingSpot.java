package ParkingSpot;

import Enums.VehicleType;

public abstract class ParkingSpot {
    private int id;
    private boolean isEmpty;
    private double price;
    private VehicleType vehicle;

    public ParkingSpot(int id, double price) {
        this.id = id;
        this.price = price;
        this.isEmpty = true; // Initially, the spot is empty
    }

    public void parkVehicle(VehicleType vehicle) {
        if (isEmpty) {
            this.vehicle = vehicle;
            this.isEmpty = false;
            System.out.println("Vehicle parked: " + vehicle);
        }
        else {
            System.out.println("Spot is already occupied.");
        }
    }
    public void unparkVehicle(){
        if(!isEmpty){
            this.vehicle = null;
            this.isEmpty = true;
            System.out.println("Spot is now empty.");
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

    public VehicleType getVehicle() {
        return vehicle;
    }
}
