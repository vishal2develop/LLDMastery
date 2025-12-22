public interface RoomVisitor {
    /**
     * For each room type, we have a method to visit it.
     * The visit method is overloaded for each room type.
     * @param room
     */
    public void visit(SingleRoom room);
    public void visit(DoubleRoom room);
    public void visit(DeluxeRoom room);
}
