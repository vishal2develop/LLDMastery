import java.time.LocalDate;

// Facade Pattern
public class HotelService {
    private ReservationService reservationService;
    private BillingService billingService;

    public HotelService(ReservationService reservationService, BillingService billingService) {
        this.reservationService = reservationService;
        this.billingService = billingService;
    }

    public Reservation reserveRoom(Guest guest, RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationService.reserveRoom(guest,roomType,checkInDate,checkOutDate);
    }
    public void checkInGuest(Reservation reservation){
        reservationService.checkInGuest(reservation);
    }
    public Bill checkOutGuest(Reservation reservation){
        reservationService.checkOutGuest(reservation);
        return billingService.generateBill(reservation);
    }
    public void cancelReservation(Reservation reservation){
        reservationService.cancelReservation(reservation);
    }
}
