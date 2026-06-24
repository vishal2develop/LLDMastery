public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    private Player currentPlayer;
    private GameStatus status;

    private final WinningStrategy winningStrategy;

    public Game(Player player1, Player player2,int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(boardSize);
        this.currentPlayer = player1;
        this.status = GameStatus.IN_PROGRESS;

        this.winningStrategy = new DefaultWinningStrategy();
    }

    public void makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Game has already ended."
            );
        }
        board.placeMove(row, col, currentPlayer);
        if(winningStrategy.hasWinner(board, currentPlayer)) {
            status = GameStatus.WON;
            return;
        } else if(board.isBoardFull()) {
            status = GameStatus.DRAW;
            return;
        }
        switchPlayers();
    }

    private void switchPlayers() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    // getters
    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
