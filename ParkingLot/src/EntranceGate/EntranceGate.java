package EntranceGate;

import Enums.VehicleType;
import Factory.ParkingSpotFactory.ParkingSpotFactory;
import Managers.ParkingSpotManager;
import ParkingSpot.ParkingSpot;
import Services.ParkingService;
import Services.TicketService;
import Ticket.Ticket;
import Vehicle.Vehicle;

import java.util.List;

public class EntranceGate {
    private int gateNo;
    private ParkingService parkingService;
    private TicketService ticketService;

    public EntranceGate(int gateNo, ParkingService parkingService, TicketService ticketService) {
        this.gateNo = gateNo;
        this.parkingService = parkingService;
        this.ticketService = ticketService;
    }

    // Process vehicle entry: find parking spot, book it, and generate a ticket
    public Ticket processVehicleEntry(Vehicle vehicle) {
        // Find & book a parking spot for the vehicle
        ParkingSpot parkingSpot = parkingService.bookSpot(vehicle);

        // Generate and return a ticket if a parking spot was successfully booked
        if (parkingSpot != null) {
            return ticketService.generateTicket(vehicle, parkingSpot, gateNo);
        }
        System.out.println("No parking spot available for vehicle: " + vehicle.getLicensePlate());
        return null;
    }

    // Process vehicle exit: unpark the vehicle using the ticket
    public void processVehicleExit(Ticket ticket) {
        parkingService.unparkVehicle(ticket);  // Unpark the vehicle using the ticket
    }

//    // Method to book a spot using the vehicle object
//    public ParkingSpot bookSpot(Vehicle vehicle) {
//        // Get the appropriate ParkingSpotManager
//        ParkingSpotManager parkingSpotManager = ParkingSpotFactory.getParkingSpotManager(vehicle.getVehicleType(), parkingSpots);
//
//        // Book the parking spot by parking the vehicle
//        ParkingSpot parkingSpot = parkingSpotManager.findSpace();
//        if (parkingSpot != null) {
//            parkingSpotManager.parkVehicle(vehicle);
//            System.out.println("Parking spot booked for vehicle: " + vehicle.getLicensePlate() + " at Gate: " + gateNo);
//        } else {
//            System.out.println("No parking spot available for vehicle: " + vehicle.getLicensePlate());
//        }
//        return parkingSpot;
//
//    }
//
//    // Generate a ticket after the parking spot is booked
//    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
//        // Generate a ticket using the ParkingSpotManager
//        if (parkingSpot != null) {
//            Ticket ticket = new Ticket(vehicle, parkingSpot,gateNo);
//            System.out.println("Ticket generated for vehicle: " + vehicle.getLicensePlate() + " at Gate: " + gateNo);
//            return ticket;
//        }
//        System.out.println("Unable to generate ticket as no spot is booked.");
//        return null;
//    }


}
