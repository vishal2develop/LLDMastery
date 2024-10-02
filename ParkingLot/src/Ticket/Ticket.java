package Ticket;

import Enums.VehicleType;
import ParkingSpot.ParkingSpot;
import Vehicle.Vehicle;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    // Static variable for auto-incrementing ticket ID
    private static final AtomicInteger ticketCounter = new AtomicInteger(0);

    private int ticketID;
    private long entryTime;
    private final VehicleType vehicleType;
    private final String vehicleLicensePlate;
    private ParkingSpot parkingSpot;

    public Ticket(VehicleType vehicleType, String vehicleLicensePlate, ParkingSpot parkingSpot) {
        this.ticketID=ticketCounter.incrementAndGet(); // unique ticketId
        this.vehicleType = vehicleType;
        this.vehicleLicensePlate = vehicleLicensePlate;
        this.parkingSpot = parkingSpot;
        this.entryTime=System.currentTimeMillis();
    }

    // Getters
    public int getTicketID() {
        return ticketID;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    //Setters

//    public void setTicketID(int ticketID) {
//        this.ticketID = ticketID;
//    }
//    public void setEntryTime(long entryTime) {
//        this.entryTime = entryTime;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    public void setParkingSpot(ParkingSpot parkingSpot) {
//        this.parkingSpot = parkingSpot;
//    }

}
