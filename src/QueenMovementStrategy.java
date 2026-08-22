import Enums.PieceColor;

public class QueenMovementStrategy implements MovementStrategy{
    @Override
    /**
     * A queen can move:
     * 1. in a straight line like a rook
     * 2. in a diagonal line like a bishop
     */
    public boolean canMove(Board board, Position from, Position to, PieceColor playerColor) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        boolean isStraightMove = (from.getRow() == to.getRow() || from.getCol() == to.getCol()) && !from.equals(to);
        boolean isDiagonalMove = rowDiff == colDiff && rowDiff >0;

        boolean isQueenMove = isStraightMove || isDiagonalMove;

        return isQueenMove && board.isPathClear(from, to) && !board.hasOwnPiece(to, playerColor);
    }
}
