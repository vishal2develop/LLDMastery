public class PiecePosition {
    private final Position position;
    private final Piece piece;

    public PiecePosition(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public Position getPosition() {
        return position;
    }
}
