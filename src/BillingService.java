public class BillingService {
    public Bill generateBill(Reservation reservation) {
        PricingStrategy pricingStrategy = getPricingStrategy(reservation.getRoom().getRoomType());
        double amount = pricingStrategy.calculatePrice(reservation);
        return new Bill(generateBillId(),amount, reservation);
    }

    private PricingStrategy getPricingStrategy(RoomType roomType){
        return switch (roomType) {
            case STANDARD -> new StandardRoomPricingStrategy();
            case DELUXE -> new DeluxeRoomPricingStrategy();
            case SUITE -> new SuiteRoomPricingStrategy();
            default -> throw new IllegalArgumentException("Invalid room type");
        };
    }

    private String generateBillId() {
        return String.valueOf(System.nanoTime());
    }
}
