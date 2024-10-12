package Services;

import Enums.VehicleType;
import Factory.ParkingSpotFactory.ParkingSpotFactory;
import Managers.ParkingSpotManager;
import ParkingSpot.ParkingSpot;
import Ticket.Ticket;
import Vehicle.Vehicle;

import java.util.List;

public class ParkingService {
    private List<ParkingSpot> parkingSpots;
    public ParkingService(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    // Method to find and book a parking spot for the vehicle
    public ParkingSpot bookSpot(Vehicle vehicle) {
        VehicleType vehicleType = vehicle.getVehicleType();
        ParkingSpotManager parkingSpotManager = ParkingSpotFactory.getParkingSpotManager(vehicleType, parkingSpots);

        // Find and return the available parking spot
        ParkingSpot parkingSpot = parkingSpotManager.findSpace();
        if (parkingSpot != null) {
            parkingSpotManager.parkVehicle(vehicle);
            System.out.println("Parking spot booked for vehicle: " + vehicle.getLicensePlate());
            return parkingSpot;
        }
        return null;
    }

    // Method to unpark the vehicle using the ticket
    public void unparkVehicle(Ticket ticket) {
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        ParkingSpotManager parkingSpotManager = ParkingSpotFactory.getParkingSpotManager(vehicleType, parkingSpots);

        // Unpark the vehicle from the spot stored in the ticket
        parkingSpotManager.unparkVehicle(ticket);
    }
}
