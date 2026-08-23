import Enums.PieceColor;

public class LegalMoveDetector {
    private final MoveValidator validator = new MoveValidator();
    private final CheckDetector checkDetector = new CheckDetector();

    /** Flow
     * scan all pieces of this color
     * try every board position as a possible move/destination
     * if the move is legal
     *  -> apply the move
     *  -> check own king safety
     *  -> undo the move
     *  -> if king is safe, return true
     *  return false
     */
    public boolean hasAnyLegalMove(Board board, PieceColor playerColor) {
        // Scan all pieces of the player's color
        for(PiecePosition piecePosition : board.getPieces()){
            Piece piece = piecePosition.getPiece();
            // if the piece is not of the player's color, skip it'
            if(piece.getColor() != playerColor){
                continue;
            }
            // get the current position of the piece
            Position from = piecePosition.getPosition();

            // scan all possible destinations
            for(int row = 0; row<8; row++){
                for(int col=0;col<8;col++){
                    // possible destination
                    Position to = new Position(row, col);

                    // First check basic movement rules.
                    if(!validator.isValidMove(board, from, to, playerColor)){
                        continue;
                    }
                    // If the movement is valid, apply it temporarily to test king safety.
                    Move move = board.movePiece(from, to);
                    boolean ownKingCheck = checkDetector.isInCheck(board, playerColor);
                    // undo the temporary move
                    board.undoMove(move);
                    // if the move is legal and the own king is not in check, return true.
                    // if even one safe move exists -> true
                    if(!ownKingCheck){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
