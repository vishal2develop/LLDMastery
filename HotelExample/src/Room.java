// Element Interface
// Element = Entity we want to perform ops on
public interface Room {
    public void accept(RoomVisitor visitor);
}
