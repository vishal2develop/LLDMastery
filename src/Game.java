public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private GameStatus status;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.board = new Board();
        this.currentPlayer = player1;
        this.status = GameStatus.IN_PROGRESS;
    }

    public void makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Game has already ended."
            );
        }
        board.placeMove(row, col, currentPlayer);
        if(board.hasWinner(currentPlayer)) {
            status = GameStatus.WON;
            return;
        } else if(board.isBoardFull()) {
            status = GameStatus.DRAW;
            return;
        }
        switchPlayers();
    }

    public void switchPlayers() {
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
