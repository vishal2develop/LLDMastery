import Enums.GameStatus;
import Enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final String gameId;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final Board board;

    private PieceColor currentTurn = PieceColor.WHITE;
    private GameStatus status = GameStatus.NOT_STARTED;
    private final List<Move> moveHistory = new ArrayList<>();

    Game(String gameId, Player whitePlayer, Player blackPlayer, Board board) {
        this.gameId = gameId;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
    }

    public void startGame() {
        System.out.println("Game started!");
        status = GameStatus.IN_PROGRESS;
        currentTurn = PieceColor.WHITE;
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public Board getBoard() {
        return board;
    }
}
