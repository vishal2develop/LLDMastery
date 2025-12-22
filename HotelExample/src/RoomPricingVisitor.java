public class RoomPricingVisitor implements RoomVisitor{
    @Override
    public void visit(SingleRoom room) {
        System.out.println("Single Room Price: 1000");
        room.roomPrice = 1000;
    }
    @Override
    public void visit(DoubleRoom room) {
        System.out.println("Double Room Price: 1500");
        room.roomPrice = 1500;
    }
    @Override
    public void visit(DeluxeRoom room) {
        System.out.println("Deluxe Room Price: 2000");
        room.roomPrice = 2000;
    }
}
