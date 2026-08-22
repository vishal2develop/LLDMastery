import Enums.PieceColor;

public class RookMovementStrategy implements MovementStrategy{

    @Override
    /**
     * A rook moves in a straight line.
     */
    public boolean canMove(Board board, Position from, Position to, PieceColor playerColor) {
        // check for straight line moves and reject if the destination is the same as the source
        boolean isStraightMove = (from.getRow() == to.getRow() || from.getCol() == to.getCol()) && !from.equals(to);
        return isStraightMove && board.isPathClear(from, to) && !board.hasOwnPiece(to, playerColor);
    }
}
