import EntranceGate.EntranceGate;
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
        // Create parking spots for two-wheelers and four-wheelers
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new TwoWheelerParkingSpot(1, 10.0));
        parkingSpots.add(new TwoWheelerParkingSpot(2, 10.0));
        parkingSpots.add(new FourWheelerParkingSpot(3, 20.0));
        parkingSpots.add(new FourWheelerParkingSpot(4, 20.0));

        // Create an entrance gate
        EntranceGate entranceGate = new EntranceGate(1, parkingSpots);

        // Create vehicles
        Vehicle bike = new TwoWheeler("Bike123");
        Vehicle car = new FourWheeler("Car456");

        // Find a space for the bike and park it
        ParkingSpot bikeSpot = entranceGate.findSpace(VehicleType.TWO_WHEELER);
        entranceGate.bookSpot(bike);
        Ticket bikeTicket = entranceGate.generateTicket(bike, bikeSpot);
        System.out.println("Ticket for Bike: " + bikeTicket.getTicketID());

        // Find a space for the car and park it
        ParkingSpot carSpot = entranceGate.findSpace(VehicleType.FOUR_WHEELER);
        entranceGate.bookSpot(car);
        Ticket carTicket = entranceGate.generateTicket(car, carSpot);
        System.out.println("Ticket for Car: " + carTicket.getTicketID());

//        // Create parking managers
//        ParkingSpotManager twoWheelerManager = new TwoWheelerParkingSpotManager(twoWheelerSpots);
//        ParkingSpotManager fourWheelerManager = new FourWheelerParkingSpotManager(fourWheelerSpots);
//
//        // Create vehicles
//        Vehicle bike = new TwoWheeler("Bike123");
//        Vehicle car = new FourWheeler("Car456");
//
//        // Park a two-wheeler
//        System.out.println("Two-Wheeler Parking:");
//        Ticket bikeTicket = twoWheelerManager.parkVehicle(bike);  // Now returns a Ticket
//        System.out.println("Ticket ID: " + bikeTicket.getTicketID());
//        System.out.println("Vehicle: " + bikeTicket.getVehicle().getLicensePlate());
//        System.out.println("Parking Spot ID: " + bikeTicket.getParkingSpot().getId());
//        System.out.println("Entry Time: " + bikeTicket.getEntryTime());
//        System.out.println("Parking Fee: $" + twoWheelerManager.calculateParkingFee());
//
//        // Park a four-wheeler
//        System.out.println("\nFour-Wheeler Parking:");
//        Ticket carTicket = fourWheelerManager.parkVehicle(car);  // Now returns a Ticket
//        System.out.println("Ticket ID: " + carTicket.getTicketID());
//        System.out.println("Vehicle: " + carTicket.getVehicle().getLicensePlate());
//        System.out.println("Parking Spot ID: " + carTicket.getParkingSpot().getId());
//        System.out.println("Entry Time: " + carTicket.getEntryTime());
//        System.out.println("Parking Fee: $" + fourWheelerManager.calculateParkingFee());
//
//        // Unpark the vehicles using the tickets
//        System.out.println("\nUnparking Vehicles:");
//        twoWheelerManager.unParkVehicle(bikeTicket);  // Use the ticket to unpark
//        fourWheelerManager.unParkVehicle(carTicket);  // Use the ticket to unpark

    }
}
