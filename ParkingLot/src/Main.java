import Enums.VehicleType;
import Managers.FourWheelerParkingSpotManager;
import Managers.ParkingSpotManager;
import Managers.TwoWheelerParkingSpotManager;
import ParkingSpot.ParkingSpot;
import ParkingSpot.TwoWheelerParkingSpot;
import ParkingSpot.FourWheelerParkingSpot;
import Ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Main {
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

        // Park a two-wheeler
        System.out.println("Two-Wheeler Parking:");
        Ticket bikeTicket = twoWheelerManager.parkVehicle(VehicleType.TWO_WHEELER, "111111");  // Now takes vehicle type and license plate
        if (bikeTicket != null) {
            System.out.println("Ticket ID: " + bikeTicket.getTicketID());
            System.out.println("Vehicle License Plate: " + bikeTicket.getVehicleLicensePlate());
            System.out.println("Parking Spot ID: " + bikeTicket.getParkingSpot().getId());
            System.out.println("Entry Time: " + bikeTicket.getEntryTime());
            System.out.println("Parking Fee: $" + twoWheelerManager.calculateParkingFee());
        }


        // Park a four-wheeler
        System.out.println("\nFour-Wheeler Parking:");
        Ticket carTicket = fourWheelerManager.parkVehicle(VehicleType.FOUR_WHEELER, "22222");  // Now takes vehicle type and license plate
        if (carTicket != null) {
            System.out.println("Ticket ID: " + carTicket.getTicketID());
            System.out.println("Vehicle License Plate: " + carTicket.getVehicleLicensePlate());
            System.out.println("Parking Spot ID: " + carTicket.getParkingSpot().getId());
            System.out.println("Entry Time: " + carTicket.getEntryTime());
            System.out.println("Parking Fee: $" + fourWheelerManager.calculateParkingFee());
        }

        // Unpark the vehicles
        System.out.println("\nUnparking Vehicles:");
        twoWheelerManager.unParkVehicle(bikeTicket);
        fourWheelerManager.unParkVehicle(carTicket);
    }
}