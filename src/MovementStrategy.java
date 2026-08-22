import Enums.PieceColor;

public interface MovementStrategy {
    boolean canMove(Board board, Position from, Position to, PieceColor playerColor);
}
