import Enums.VehicleType;
import Managers.FourWheelerParkingSpotManager;
import Managers.ParkingSpotManager;
import Managers.TwoWheelerParkingSpotManager;
import ParkingSpot.ParkingSpot;
import ParkingSpot.TwoWheelerParkingSpot;
import ParkingSpot.FourWheelerParkingSpot;
import Ticket.Ticket;
import Vehicle.FourWheeler;
import Vehicle.TwoWheeler;
import Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleTicketMain {
    public static void main(String[] args) {
        // Create parking spots for two-wheelers
        List<ParkingSpot> twoWheelerSpots = new ArrayList<>();
        twoWheelerSpots.add(new TwoWheelerParkingSpot(1, 10.0));
        twoWheelerSpots.add(new TwoWheelerParkingSpot(2, 10.0));

        // Create parking spots for four-wheelers
        List<ParkingSpot> fourWheelerSpots = new ArrayList<>();
        fourWheelerSpots.add(new FourWheelerParkingSpot(1, 20.0));
        fourWheelerSpots.add(new FourWheelerParkingSpot(2, 20.0));

        // Create parking managers
        ParkingSpotManager twoWheelerManager = new TwoWheelerParkingSpotManager(twoWheelerSpots);
        ParkingSpotManager fourWheelerManager = new FourWheelerParkingSpotManager(fourWheelerSpots);

        // Create vehicles
        Vehicle bike = new TwoWheeler("Bike123");
        Vehicle car = new FourWheeler("Car456");

        // Park a two-wheeler
        System.out.println("Two-Wheeler Parking:");
        Ticket bikeTicket = twoWheelerManager.parkVehicle(bike);  // Now returns a Ticket
        System.out.println("Ticket ID: " + bikeTicket.getTicketID());
        System.out.println("Vehicle: " + bikeTicket.getVehicle().getLicensePlate());
        System.out.println("Parking Spot ID: " + bikeTicket.getParkingSpot().getId());
        System.out.println("Entry Time: " + bikeTicket.getEntryTime());
        System.out.println("Parking Fee: $" + twoWheelerManager.calculateParkingFee());

        // Park a four-wheeler
        System.out.println("\nFour-Wheeler Parking:");
        Ticket carTicket = fourWheelerManager.parkVehicle(car);  // Now returns a Ticket
        System.out.println("Ticket ID: " + carTicket.getTicketID());
        System.out.println("Vehicle: " + carTicket.getVehicle().getLicensePlate());
        System.out.println("Parking Spot ID: " + carTicket.getParkingSpot().getId());
        System.out.println("Entry Time: " + carTicket.getEntryTime());
        System.out.println("Parking Fee: $" + fourWheelerManager.calculateParkingFee());

        // Unpark the vehicles using the tickets
        System.out.println("\nUnparking Vehicles:");
        twoWheelerManager.unParkVehicle(bikeTicket);  // Use the ticket to unpark
        fourWheelerManager.unParkVehicle(carTicket);  // Use the ticket to unpark

    }
}
