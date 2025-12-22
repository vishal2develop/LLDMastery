public class SingleRoom implements Room{
    double roomPrice;
    @Override
    public void accept(RoomVisitor visitor) {
        System.out.println("Single Room");
        // perform operation via visitor
        // this = Holds the current object
        visitor.visit(this);
    }
}
