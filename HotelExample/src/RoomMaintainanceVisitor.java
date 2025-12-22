public class RoomMaintainanceVisitor implements RoomVisitor{
    @Override
    public void visit(SingleRoom room) {
        System.out.println("Performing maintenance on Single Room.");
    }
    @Override
    public void visit(DoubleRoom room) {
        System.out.println("Performing maintenance on Double Room.");
    }
    @Override
    public void visit(DeluxeRoom room) {
        System.out.println("Performing maintenance on Deluxe Room.");
    }
}
