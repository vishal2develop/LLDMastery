import Enums.PieceColor;
import Enums.PieceType;

public class Main {
    public static void main(String[] args) {
        runSelfCheckRollbackDemo();
        System.out.println();
        runCheckmateDemo();
        System.out.println();
        runStalemateDemo();
    }

    private static void runSelfCheckRollbackDemo() {
        Board board = Board.createEmptyBoard();

        board.placePiece(new Position(7, 4), new Piece(PieceType.KING, PieceColor.WHITE));
        board.placePiece(new Position(0, 0), new Piece(PieceType.KING, PieceColor.BLACK));

        board.placePiece(new Position(6, 4), new Piece(PieceType.ROOK, PieceColor.WHITE));
        board.placePiece(new Position(0, 4), new Piece(PieceType.ROOK, PieceColor.BLACK));

        Game game = createGame("G-1", board);
        game.startGame();

        boolean result = game.makeMove(
                new Position(6, 4),
                new Position(6, 5)
        );

        printResult("Self-check rollback demo", game, result);
        System.out.println("White rook restored: " + (game.getBoard().getPiece(new Position(6, 4)) != null));
        System.out.println("Destination empty: " + game.getBoard().isPositionEmpty(new Position(6, 5)));
    }

    private static void runCheckmateDemo() {
        Board board = Board.createEmptyBoard();

        board.placePiece(new Position(1, 2), new Piece(PieceType.KING, PieceColor.WHITE));
        board.placePiece(new Position(0, 0), new Piece(PieceType.KING, PieceColor.BLACK));
        board.placePiece(new Position(2, 1), new Piece(PieceType.QUEEN, PieceColor.WHITE));

        Game game = createGame("G-2", board);
        game.startGame();

        boolean result = game.makeMove(
                new Position(2, 1),
                new Position(0, 1)
        );

        printResult("Checkmate demo", game, result);
    }

    private static void runStalemateDemo() {
        Board board = Board.createEmptyBoard();

        board.placePiece(new Position(2, 2), new Piece(PieceType.KING, PieceColor.WHITE));
        board.placePiece(new Position(0, 0), new Piece(PieceType.KING, PieceColor.BLACK));
        board.placePiece(new Position(2, 3), new Piece(PieceType.QUEEN, PieceColor.WHITE));

        Game game = createGame("G-3", board);
        game.startGame();

        boolean result = game.makeMove(
                new Position(2, 3),
                new Position(1, 2)
        );

        printResult("Stalemate demo", game, result);
    }

    private static Game createGame(String gameId, Board board) {
        Player whitePlayer = new Player("P-1","Vishal", PieceColor.WHITE);
        Player blackPlayer = new Player("P-2","Rohan", PieceColor.BLACK);

        return new Game(gameId, whitePlayer, blackPlayer, board);
    }

    private static void printResult(String title, Game game, boolean moveResult) {
        System.out.println("== " + title + " ==");
        System.out.println("Move result: " + moveResult);
        System.out.println("Status: " + game.getStatus());
        System.out.println("Current turn: " + game.getCurrentTurn());
        System.out.println("History size: " + game.getMoveHistory().size());
    }
}
