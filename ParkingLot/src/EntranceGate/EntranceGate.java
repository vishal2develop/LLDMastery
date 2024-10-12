package EntranceGate;

import Enums.VehicleType;
import Factory.ParkingSpotFactory.ParkingSpotFactory;
import Managers.ParkingSpotManager;
import ParkingSpot.ParkingSpot;
import Ticket.Ticket;
import Vehicle.Vehicle;

import java.util.List;

public class EntranceGate {
    private int gateNo;
    private List<ParkingSpot> parkingSpots; // List of available parking spots

    public EntranceGate(int gateNo, List<ParkingSpot> parkingSpots) {
        this.gateNo = gateNo;
        this.parkingSpots = parkingSpots;
    }

    // Method to find the parking space based on the vehicle type and entrance gate number
    public ParkingSpot findSpace(VehicleType vehicleType) {
        // Get the appropriate ParkingSpotManager from the factory
        ParkingSpotManager parkingSpotManager = ParkingSpotFactory.getParkingSpotManager(vehicleType, parkingSpots);

        // Find and return the parking space
        return parkingSpotManager.findSpace();
    }

    // Method to book a spot using the vehicle object
    public void bookSpot(Vehicle vehicle) {
        // Get the appropriate ParkingSpotManager
        ParkingSpotManager parkingSpotManager = ParkingSpotFactory.getParkingSpotManager(vehicle.getVehicleType(), parkingSpots);

        // Book the parking spot by parking the vehicle
        parkingSpotManager.parkVehicle(vehicle);
    }

    // Method to generate a parking ticket
    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        // Generate a ticket using the ParkingSpotManager
        return new Ticket(vehicle, parkingSpot);
    }

}
