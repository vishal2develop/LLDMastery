import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        // ask for board size
//        System.out.println(" Enter board size:");
//        int boardSize = Integer.parseInt(System.console().readLine());


        Player player1 = new Player("Vishal", Symbol.X);
        Player player2 = new Player("Rahul", Symbol.O);
        Game game = new Game(player1, player2, 3);


        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X wins

        game.getBoard().printBoard();
        System.out.println("Game Status: " + game.getStatus());

        if (game.getStatus() == GameStatus.WON) {
            System.out.println("Winner: " + game.getCurrentPlayer().getPlayerName());
        }
    }
}
