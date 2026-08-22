import Enums.PieceColor;

public class KnightMovementStrategy implements MovementStrategy{
    @Override
    /**
     * A knight moves in an L-shape: two squares along one axis and one square along the other.
     * Any piece cannot move upon itself.
     * rowDiff = 2 and colDiff = 1 -> 2 squares in a straight line
     * rowDiff = 1 and colDiff = 2 -> 1 square in a diagonal line
     */
    public boolean canMove(Board board, Position from, Position to, PieceColor playerColor) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        boolean isKnightMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        return isKnightMove && !board.hasOwnPiece(to, playerColor);
    }
}
