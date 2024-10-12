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
    public ParkingSpot bookSpot(Vehicle vehicle) {
        // Get the appropriate ParkingSpotManager
        ParkingSpotManager parkingSpotManager = ParkingSpotFactory.getParkingSpotManager(vehicle.getVehicleType(), parkingSpots);

        // Book the parking spot by parking the vehicle
        ParkingSpot parkingSpot = parkingSpotManager.findSpace();
        if (parkingSpot != null) {
            parkingSpotManager.parkVehicle(vehicle);
            System.out.println("Parking spot booked for vehicle: " + vehicle.getLicensePlate() + " at Gate: " + gateNo);
        } else {
            System.out.println("No parking spot available for vehicle: " + vehicle.getLicensePlate());
        }
        return parkingSpot;

    }

    // Generate a ticket after the parking spot is booked
    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        // Generate a ticket using the ParkingSpotManager
        if (parkingSpot != null) {
            Ticket ticket = new Ticket(vehicle, parkingSpot,gateNo);
            System.out.println("Ticket generated for vehicle: " + vehicle.getLicensePlate() + " at Gate: " + gateNo);
            return ticket;
        }
        System.out.println("Unable to generate ticket as no spot is booked.");
        return null;
    }


}
