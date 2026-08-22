import Enums.PieceColor;

public class PawnMovementStrategy implements MovementStrategy{

    @Override
    /**
     * A pawn moves:
     * 1. one-step forward
     * 2. two-step forward from starting row
     * 3. diagonal capture
     * 4. en passant (Not in scope for now)
     * 5. promotion (Not in scope for now)
     */
    public boolean canMove(Board board, Position from, Position to, PieceColor playerColor) {
        // WHITE moves upward: row decreases by 1
        // BLACK moves downward: row increases by 1
        int direction = playerColor == PieceColor.WHITE ? -1 : 1;

        // white pawn starts at row 6, black pawn starts at row 1
        int startRow = playerColor == PieceColor.WHITE ? 6 : 1;


        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // one-step forward is only valid if the pawn is moving straight forward and the destination square is empty
        boolean oneStepForward =
                rowDiff == direction &&
                        colDiff == 0 &&
                        board.isPositionEmpty(to);

        // two-step forward is only valid if the pawn is on the starting row and the destination square is empty
        boolean twoStepForward = false;

        if (from.getRow() == startRow && rowDiff == 2 * direction && colDiff == 0) {
            Position middle = new Position(from.getRow() + direction, from.getCol());
            twoStepForward = board.isPositionEmpty(middle) && board.isPositionEmpty(to);
        }

        // diagonal capture is only valid if the pawn is moving diagonally and the destination square is occupied by an opponent piece
        boolean diagonalCapture =
                rowDiff == direction &&
                        Math.abs(colDiff) == 1 &&
                        board.hasOpponentPiece(to, playerColor);

        return oneStepForward || twoStepForward || diagonalCapture;
    }
}
