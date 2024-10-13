package ExitGate;

import Enums.PaymentType;
import Enums.VehicleType;
import Services.ParkingService;
import Services.PaymentService;
import Services.TicketService;
import Strategies.PricingStrategy.HourlyPricingStrategy;
import Strategies.PricingStrategy.MinutePricingStrategy;
import Ticket.Ticket;

public class ExitGate {
    private int gateNo;
    private ParkingService parkingService;
    private TicketService ticketService;
    private PaymentService paymentService;
    protected double parkingCost;
    public ExitGate(int gateNo, ParkingService parkingService, TicketService ticketService, PaymentService paymentService) {
        this.gateNo = gateNo;
        this.parkingService = parkingService;
        this.ticketService = ticketService;
        this.paymentService = paymentService;
    }

    // Process vehicle exit: unpark the vehicle using the ticket
    public void processVehicleExit(Ticket ticket, PaymentType paymentType) {
        // 1. Free Parking Spot
        parkingService.unparkVehicle(ticket);  // Unpark the vehicle using the ticket
        // 2. Calculate Price
        double parkingSpotPrice = ticket.getParkingSpot().getPrice();
        if (ticket.getVehicle().getVehicleType() == VehicleType.TWO_WHEELER){
            HourlyPricingStrategy hourlyPricingStrategy = new HourlyPricingStrategy();
            parkingCost = hourlyPricingStrategy.calculateCost(ticket.getEntryTime(),parkingSpotPrice);
        }
        else if (ticket.getVehicle().getVehicleType() == VehicleType.FOUR_WHEELER){
            MinutePricingStrategy minutePricingStrategy = new MinutePricingStrategy();
            parkingCost = minutePricingStrategy.calculateCost(ticket.getEntryTime(),parkingSpotPrice);
        }
        else {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
        // 3
        // . Collect Payment
        paymentService.processPayment(paymentType,parkingCost);

    }

}
