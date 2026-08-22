import Enums.PieceColor;

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

    // checks if a position has the players own piece instead of a piece of the opponent
    // you cannot move upon your own piece
    public boolean hasOwnPiece(Position position, PieceColor playerColor){
        Piece piece = pieces.get(position);
        return piece!=null && piece.getColor() == playerColor;

    }

    public boolean isPathClear(Position from, Position to){

        // Find the direction in which the row and column must change.
        // Integer.compare returns:
        //   -1 when the destination value is smaller than the starting value
        //    0 when the value does not change
        //    1 when the destination value is larger than the starting value
        // For example, rowStep = 1 means move toward larger row numbers,
        // while colStep = -1 means move toward smaller column numbers.
        int rowStep = Integer.compare(to.getRow(), from.getRow());
        int colStep = Integer.compare(to.getCol(), from.getCol());

        // Start at the square immediately after the starting position.
        // The starting square already contains the piece that is moving,
        // so it does not need to be checked.
        int currentRow = from.getRow() + rowStep;
        int currentCol = from.getCol() + colStep;

        // Check every square between 'from' and 'to'.
        // The loop stops when the destination is reached, so the destination
        // itself is deliberately not checked here. A destination may contain
        // an opponent's piece, because that piece can be captured.
        while(currentRow != to.getRow() || currentCol != to.getCol()){
            // Create a Position for the square currently being inspected.
            Position currentPosition = new Position(currentRow, currentCol);

            // A piece cannot jump over another piece. Therefore, one occupied
            // square anywhere between the start and destination blocks the move.
            if(!isPositionEmpty(currentPosition)){
                return false;
            }

            // Move to the next square along the same row/column direction.
            currentRow += rowStep;
            currentCol += colStep;
        }

        // Every square between the two positions was empty,
        // so the path is clear for this piece to move through.
        return true;
    }

    // checks if a position has the opponents piece instead of a piece of the player.
    // Accepts opponent color as parameter
    public boolean hasOpponentPiece(Position position,PieceColor playerColor){
        Piece piece = getPiece(position);
        // if the position is not empty and occupied by the opponent, return true.
        // Means it can be captured by a valid move.
        return piece!=null && piece.getColor()!=playerColor;
    }

}
