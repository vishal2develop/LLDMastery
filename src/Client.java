public class Client {

    public static void main(String[] args) throws InterruptedException {

        playGameDemo();

        System.out.println();

        singletonConcurrencyDemo();
    }

    /**
     * Demonstrates a complete Tic Tac Toe game.
     */
    private static void playGameDemo() {

        System.out.println("=== Tic Tac Toe Demo ===");

        Player player1 = new Player("Vishal", Symbol.X);
        Player player2 = new Player("Rahul", Symbol.O);

        GameController gameController =
                GameController.getInstance();

        String gameId =
                gameController.createGame(
                        player1,
                        player2,
                        3
                );

        playWinningGame(gameController, gameId);

        gameController.printBoard(gameId);

        GameStatus status =
                gameController.getGameStatus(gameId);

        System.out.println("\nGame Status: " + status);

        if (status == GameStatus.WON) {
            System.out.println(
                    "Winner: "
                            + gameController.getWinner(gameId)
            );
        }
    }

    /**
     * Plays a predefined winning sequence.
     */
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

    /**
     * Demonstrates that GameController
     * is a Singleton by printing the
     * instance hash code from multiple threads.
     */
    private static void singletonConcurrencyDemo()
            throws InterruptedException {

        System.out.println("\n=== Singleton Concurrency Demo ===");

        Runnable task = () -> {
            GameController controller =
                    GameController.getInstance();

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> "
                            + System.identityHashCode(controller)
            );
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}