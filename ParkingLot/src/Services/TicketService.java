package Services;

import ParkingSpot.ParkingSpot;
import Ticket.Ticket;
import Vehicle.Vehicle;

public class TicketService {
    // Method to generate a parking ticket
    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot, int gateNo) {
        Ticket ticket = new Ticket(vehicle, parkingSpot, gateNo);
        System.out.println("Ticket generated for vehicle: " + vehicle.getLicensePlate() + " at Gate: " + gateNo);
        return ticket;
    }
}
