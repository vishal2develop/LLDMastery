import Enums.PieceColor;
import Enums.PieceType;

import java.util.EnumMap;
import java.util.Map;

/**
 * MoveValidator separates generic move request validation from piece-specific movement rules.
 * This keeps strategies focused on movement geometry, while the validator handles common concerns like source ownership and strategy dispatch.
 */
public class MoveValidator {
    private final Map<PieceType, MovementStrategy> movementStrategies;

    public MoveValidator(){
        // EnumMap is used to associate a movement strategy with each piece type
        this.movementStrategies = new EnumMap<>(PieceType.class);
        movementStrategies.put(PieceType.PAWN, new PawnMovementStrategy());
        movementStrategies.put(PieceType.KNIGHT, new KnightMovementStrategy());
        movementStrategies.put(PieceType.BISHOP, new BishopMovementStrategy());
        movementStrategies.put(PieceType.ROOK, new RookMovementStrategy());
        movementStrategies.put(PieceType.QUEEN, new QueenMovementStrategy());
        movementStrategies.put(PieceType.KING, new KingMovementStrategy());
    }

    public boolean isValidMove(Board board, Position from, Position to, PieceColor playerColor) {
        Piece piece = board.getPiece(from);

        // if the source position is empty, the move is invalid
        if(piece == null){
            return false;
        }

        // if the source position is occupied by the opponent, the move is invalid
        if (piece.getColor() != playerColor){
            return false;
        }

        // if the source and destination positions are the same, the move is invalid
        if(from.equals(to)){
            return false;
        }

        // get the movement strategy for the piece type
        MovementStrategy strategy = movementStrategies.get(piece.getType());
        // make the move
        return strategy.canMove(board, from, to, playerColor);

    }
}
