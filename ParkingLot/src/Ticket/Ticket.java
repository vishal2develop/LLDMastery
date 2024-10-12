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
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private final int gateNo;  // Information about which gate the vehicle entered from

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot, int gateNo) {
        this.ticketID=ticketCounter.incrementAndGet(); // unique ticketId
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime=System.currentTimeMillis();
        this.gateNo= gateNo;
    }

    // Getters
    public int getTicketID() {
        return ticketID;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public int getGateNo() {
        return gateNo;
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
