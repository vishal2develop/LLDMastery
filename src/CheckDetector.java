import Enums.PieceColor;
import Enums.PieceType;

/**
 * PseudoCode:
 * 1. Find my king.
 * 2. Look at every enemy piece.
 * 3. Ask if any enemy piece can attack my king's square.
 *      yes -> king is in check
 *      no  -> king is not in check
 *
 * This class only answers: "is the king currently under attack?"
 * It does not decide checkmate, stalemate, or whether a move should be allowed.
 */
public class CheckDetector {
    private final MoveValidator validator = new MoveValidator();

    public boolean isInCheck(Board board, PieceColor kingColor) {
        // First find the king for the color we are checking.
        // Eg: if kingColor is WHITE, this gives us the white king's current position.
        Position kingPosition = findKing(board, kingColor);

        // Now scan all pieces on the board and check only the opponent pieces.
        for (PiecePosition piecePosition : board.getPieces()) {
            // We have found king's position, so we only care about the opposite color.
            // Eg: if the king is white, only black pieces can give check.
            if (piecePosition.getPiece().getColor() != kingColor){
                // If this opponent piece can attack the king's square, king is in check.
                if(canAttack(board, piecePosition, kingPosition)){
                    return true;
                }
            }
        }
        return false;
    }

    private Position findKing(Board board, PieceColor kingColor){
        // Board owns the pieces, so to find a king we scan the current board state.
        // In a valid chess game, each color must have exactly one king.
        for(PiecePosition piecePosition : board.getPieces()){
            Piece piece = piecePosition.getPiece();
            if(piece.getType() == PieceType.KING && piece.getColor() == kingColor){
                return piecePosition.getPosition();
            }
        }
        throw new IllegalArgumentException("No king found for color " + kingColor);
    }

    private boolean canAttack(Board board, PiecePosition attacker, Position kingPosition){
        // Attacker is an opponent piece.
        // We now check whether this piece can attack the king's position.
        Piece piece = attacker.getPiece();

        // Pawn is special.
        // It moves forward, but it attacks diagonally.
        // So we should not reuse PawnMovementStrategy for check detection.
        if(piece.getType() == PieceType.PAWN){
            return canPawnAttack(attacker, kingPosition);
        }

        // For non-pawn pieces, attack and movement are the same shape.
        // Eg: rook attacks like rook moves, bishop attacks like bishop moves.
        // So we can reuse MoveValidator for those pieces.
        return validator.isValidMove(
                board,
                attacker.getPosition(),
                kingPosition,
                piece.getColor()
        );
    }

    private boolean canPawnAttack(PiecePosition attacker, Position kingPosition){
        PieceColor attackerColor = attacker.getPiece().getColor();
        Position attackerPosition = attacker.getPosition();

        // White pawns attack one row upward.
        // Black pawns attack one row downward.
        int direction = attackerColor == PieceColor.WHITE ? -1 : 1;

        // Row difference tells whether the king is one row in the pawn's attack direction.
        // Column difference tells whether the king is diagonally left/right from the pawn.
        int rowDiff = kingPosition.getRow() - attackerPosition.getRow();
        int colDiff = kingPosition.getCol() - attackerPosition.getCol();

        // A pawn attacks only one row forward and one column diagonally.
        return rowDiff == direction && Math.abs(colDiff) == 1;
    }
}
