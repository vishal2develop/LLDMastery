import EntranceGate.EntranceGate;
import Enums.PaymentType;
import Enums.VehicleType;
import ExitGate.ExitGate;
import Managers.FourWheelerParkingSpotManager;
import Managers.ParkingSpotManager;
import Managers.TwoWheelerParkingSpotManager;
import ParkingSpot.ParkingSpot;
import ParkingSpot.TwoWheelerParkingSpot;
import ParkingSpot.FourWheelerParkingSpot;
import Services.ParkingService;
import Services.PaymentService;
import Services.TicketService;
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

        // Create services
        ParkingService parkingService = new ParkingService(parkingSpots);
        TicketService ticketService = new TicketService();
        PaymentService paymentService = new PaymentService();

        // Create entrance & exit gate
        EntranceGate entranceGate = new EntranceGate(1, parkingService,ticketService);
        ExitGate exitGate = new ExitGate(1, parkingService,ticketService,paymentService);

        // Create vehicles
        Vehicle bike = new TwoWheeler("Bike123");
        Vehicle car = new FourWheeler("Car456");

        // Step 5: Process vehicle entry for the bike
        System.out.println("\nProcessing entry for Bike...");
        Ticket bikeTicket = entranceGate.processVehicleEntry(bike);
        if (bikeTicket != null) {
            System.out.println("Ticket ID: " + bikeTicket.getTicketID() + ", Vehicle: " + bikeTicket.getVehicle().getLicensePlate() + ", Spot: " + bikeTicket.getParkingSpot().getId());
        }

        // Step 6: Process vehicle entry for the car
        System.out.println("\nProcessing entry for Car...");
        Ticket carTicket = entranceGate.processVehicleEntry(car);
        if (carTicket != null) {
            System.out.println("Ticket ID: " + carTicket.getTicketID() + ", Vehicle: " + carTicket.getVehicle().getLicensePlate() + ", Spot: " + carTicket.getParkingSpot().getId());
        }

        // Step 7: Unpark the bike using the ticket
        System.out.println("\nUnparking Bike...");
        if (bikeTicket != null) {
            exitGate.processVehicleExit(bikeTicket, PaymentType.UPI);  // Unpark the bike using its ticket
        }

        System.out.println("\nProcessing entry for Bike2...");
        Ticket bikeTicket2 = entranceGate.processVehicleEntry(bike);
        if (bikeTicket2 != null) {
            System.out.println("Ticket ID: " + bikeTicket2.getTicketID() + ", Vehicle: " + bikeTicket2.getVehicle().getLicensePlate() + ", Spot: " + bikeTicket2.getParkingSpot().getId());
        }



        // Step 8: Unpark the car using the ticket
        System.out.println("\nUnparking Car...");
        if (carTicket != null) {
            exitGate.processVehicleExit(carTicket,PaymentType.CASH);  // Unpark the car using its ticket
        }

        System.out.println("\nUnparking Bike2...");
        if (bikeTicket2 != null) {
            exitGate.processVehicleExit(bikeTicket2,PaymentType.UPI);  // Unpark the bike using its ticket
        }

    }
}
