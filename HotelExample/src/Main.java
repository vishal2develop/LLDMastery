//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create Rooms
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room deluxeRoom = new DeluxeRoom();

        // Create Visitors
        RoomVisitor roomVisitor = new RoomPricingVisitor();
        RoomVisitor roomMaintainanceVisitor = new RoomMaintainanceVisitor();

        // Perform Operations on Rooms
        singleRoom.accept(roomVisitor);
        doubleRoom.accept(roomVisitor);
        deluxeRoom.accept(roomVisitor);
        System.out.println("=========================");
        singleRoom.accept(roomMaintainanceVisitor);
        doubleRoom.accept(roomMaintainanceVisitor);
        deluxeRoom.accept(roomMaintainanceVisitor);
        System.out.println("=========================");

    }
}