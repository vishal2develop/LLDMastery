import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameController {
    // gameId -> Game
    // final because gameId never changes
    private final ConcurrentHashMap<String, Game> games;

    private GameController() {
        this.games = new ConcurrentHashMap<>();
    }

    /**
     * Inner helper class is loaded only when
     * getInstance() is called.
     */
    private static class Holder {
        private static final GameController INSTANCE =
                new GameController();
    }


    // Bill Pugh Singleton pattern - For multi-threaded environments.
    // Central registry for all active games.
    public static GameController getInstance() {
        return Holder.INSTANCE;
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

    // Two different games can proceed concurrently.
    public void makeMove(String gameId, int row, int col) {
        Game game = getExistingGame(gameId);
        synchronized (game) {
            game.makeMove(row, col);
        }
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
