import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameController {
    private static GameController instance;
    // gameId -> Game
    // final because gameId never changes
    private final Map<String, Game> games;

    private GameController() {
        this.games = new HashMap<>();
    }

    // singleton pattern
    // Central registry for all active games.
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private Game getExistingGame(String gameId) {
        Game game = games.get(gameId);

        if (game == null) {
            throw new IllegalArgumentException("Game not found");
        }

        return game;
    }

    public String createGame(Player player1, Player player2, int boardSize){
        String gameId = UUID.randomUUID().toString();
        Game game = new Game(player1, player2, boardSize);
        games.put(gameId, game);
        return gameId;
    }

    public void makeMove(String gameId, int row, int col) {
        getExistingGame(gameId).makeMove(row, col);
    }

    public GameStatus getGameStatus(String gameId){
        return getExistingGame(gameId).getStatus();
    }

    public Game getGame(String gameId){
        return getExistingGame(gameId);
    }

    public Board getBoard(String gameId){
        return getExistingGame(gameId).getBoard();
    }

    public void printBoard(String gameId){
        getExistingGame(gameId).getBoard().printBoard();
    }

    public String getWinner(String gameId){
        Game game = getExistingGame(gameId);
        return game.getCurrentPlayer().getSymbol() + " " + game.getCurrentPlayer().getPlayerName();
    }



}
