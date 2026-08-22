import java.util.HashMap;
import java.util.Map;

public class Board {
    // Board owns pieces and also the positions of pieces
    private final Map<Position, Piece> pieces;

    public Board() {
        this.pieces = new HashMap<>();
    }

    public static Board createStandardBoard(){
        return new Board();
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    public void placePiece(Position position, Piece piece) {
        if (!isPositionEmpty(position)) {
            throw new IllegalArgumentException("Position is already occupied");
        }
        pieces.put(position, piece);
    }

    public boolean isPositionEmpty(Position position) {
        return pieces.get(position) == null;
    }

    public Piece removePiece(Position position) {
        return pieces.remove(position);
    }

}
