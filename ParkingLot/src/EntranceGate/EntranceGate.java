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

}
