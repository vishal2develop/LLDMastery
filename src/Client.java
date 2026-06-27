
public class Client {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        // ask for board size
//        System.out.println(" Enter board size:");
//        int boardSize = Integer.parseInt(System.console().readLine());


        Player player1 = new Player("Vishal", Symbol.X);
        Player player2 = new Player("Rahul", Symbol.O);

        GameController gameController = GameController.getInstance();
        System.out.println("Creating a new game...");
        String gameId = gameController.createGame(player1, player2, 3);
        System.out.println("Created game with ID: " + gameId);

        playWinningGame(gameController, gameId);

        // Print the board
        gameController.printBoard(gameId);
        System.out.println();

        GameStatus status = gameController.getGameStatus(gameId);
        System.out.println("Game Status: " + status);

        if (status == GameStatus.WON) {
            System.out.println("Winner: " + gameController.getWinner(gameId));
        }
    }

    private static void playWinningGame(
            GameController controller,
            String gameId
    ) {
        controller.makeMove(gameId, 0, 0);
        controller.makeMove(gameId, 1, 0);
        controller.makeMove(gameId, 0, 1);
        controller.makeMove(gameId, 1, 1);
        controller.makeMove(gameId, 0, 2);
    }
}
