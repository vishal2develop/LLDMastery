import Enums.PieceColor;

public class LegalMoveDetector {
    private final MoveValidator validator = new MoveValidator();
    private final CheckDetector checkDetector = new CheckDetector();

    /**
     * Flow:
     * 1. Pick each piece of this player.
     * 2. Try every board square as a destination.
     * 3. Apply basic-valid moves temporarily.
     * 4. Undo the move after checking king safety.
     * 5. Return true if even one safe move exists.
     */
    public boolean hasAnyLegalMove(Board board, PieceColor playerColor) {
        for(PiecePosition piecePosition : board.getPieces()){
            Piece piece = piecePosition.getPiece();

            // If the piece is not of the player's color, skip it.
            if(piece.getColor() != playerColor){
                continue;
            }

            // Get the current position of the player's piece.
            Position from = piecePosition.getPosition();

            // Try every square as a possible destination.
            for(int row = 0; row<8; row++){
                for(int col=0;col<8;col++){
                    Position to = new Position(row, col);

                    // First check basic movement rules.
                    if(!validator.isValidMove(board, from, to, playerColor)){
                        continue;
                    }
                    // If the movement is valid, apply it temporarily to test king safety.
                    Move move = board.movePiece(from, to);
                    boolean ownKingCheck = checkDetector.isInCheck(board, playerColor);

                    // Always undo the temporary move before returning or trying the next square.
                    board.undoMove(move);

                    // If own king is safe after this trial move, at least one legal move exists.
                    if(!ownKingCheck){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
