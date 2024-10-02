package Ticket;

import ParkingSpot.ParkingSpot;
import Vehicle.Vehicle;

public class Ticket {
    private int ticketID;
    private long entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketID=1;
        this.vehicle = vehicle;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    //Setters

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

}
