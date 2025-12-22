public class DeluxeRoom implements Room{
    double roomPrice;
    @Override
    public void accept(RoomVisitor visitor) {
        System.out.println("Deluxe Room");
        // perform operation via visitor
        // this = Holds the current object
        visitor.visit(this);
    }
}
