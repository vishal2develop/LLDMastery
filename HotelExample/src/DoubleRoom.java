public class DoubleRoom implements Room{
    double roomPrice;
    @Override
    public void accept(RoomVisitor visitor) {
        System.out.println("Double Room");
        // perform operation via visitor
        // this = Holds the current object
        visitor.visit(this);
    }
}
