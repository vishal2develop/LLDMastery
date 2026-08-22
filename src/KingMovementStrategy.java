import Enums.PieceColor;

public class KingMovementStrategy implements MovementStrategy{
    @Override
    /**
     * A king moves one square in any direction.
     * Any piece cannot move upon itself.
     * rowDiff <= 1 -> The king moved at most one row up/down.
     * colDiff <= 1 -> The king moved at most one column left/right.
     * Together, these allow all 8 nearby squares around the king.
     * !(rowDiff == 0 && colDiff == 0) -> This rejects staying in the same place.
     * The destination is one of the 8 squares surrounding the king, but not the king's current square.
     */
    public boolean canMove(Board board, Position from, Position to, PieceColor playerColor) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        // The king must move to one of the eight adjacent squares.
        boolean isKingMove = rowDiff<=1 && colDiff<=1 && !(rowDiff==0 && colDiff==0);
        return isKingMove && !board.hasOwnPiece(to, playerColor);
    }
}
