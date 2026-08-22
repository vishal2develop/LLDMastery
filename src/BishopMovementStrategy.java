import Enums.PieceColor;

public class BishopMovementStrategy implements MovementStrategy{
    @Override
    /**
     * A bishop moves in a diagonal line.
     * The row and column differences must be equal. eg: (4,4) -> (2,2) -= valid
     * (4,4) -> (2,3) -= invalid
     */
    public boolean canMove(Board board, Position from, Position to, PieceColor playerColor) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        // rowDiff >0 because if from and to are same square, the the difference will be 0, but that is not a valid move.
        // Hence we check for rowDiff >0
        boolean isBishopMove = rowDiff == colDiff && rowDiff >0;
        return isBishopMove && board.isPathClear(from, to) && !board.hasOwnPiece(to, playerColor);
    }
}
