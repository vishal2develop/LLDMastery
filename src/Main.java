import Enums.PieceColor;
import Enums.PieceType;

public class Main {
    public static void main(String[] args) {
        runSelfCheckRollbackDemo();
        System.out.println();
        runCheckmateDemo();
        System.out.println();
        runStalemateDemo();
        System.out.println();
        runPromotionDemo();
        System.out.println();
        runKingSideCastlingDemo();
        System.out.println();
        runQueenSideCastlingDemo();
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

    private static void runPromotionDemo() {
        Board board = Board.createEmptyBoard();

        board.placePiece(new Position(7, 7), new Piece(PieceType.KING, PieceColor.WHITE));
        board.placePiece(new Position(7, 0), new Piece(PieceType.KING, PieceColor.BLACK));

        // White pawn is one step away from promotion.
        // White moves upward, so reaching row 0 should promote it.
        board.placePiece(new Position(1, 4), new Piece(PieceType.PAWN, PieceColor.WHITE));

        Game game = createGame("G-4", board);
        game.startGame();

        boolean result = game.makeMove(
                new Position(1, 4),
                new Position(0, 4)
        );

        printResult("Promotion demo", game, result);
        printPromotedPiece(game, new Position(0, 4));
    }

    private static void runKingSideCastlingDemo() {
        Board board = Board.createEmptyBoard();

        board.placePiece(new Position(7, 4), new Piece(PieceType.KING, PieceColor.WHITE));
        board.placePiece(new Position(7, 7), new Piece(PieceType.ROOK, PieceColor.WHITE));
        board.placePiece(new Position(0, 4), new Piece(PieceType.KING, PieceColor.BLACK));

        Game game = createGame("G-5", board);
        game.startGame();

        boolean result = game.makeMove(
                new Position(7, 4),
                new Position(7, 6)
        );

        printResult("King-side castling demo", game, result);
        System.out.println("White king castled: " + hasPiece(game.getBoard(), new Position(7, 6), PieceType.KING, PieceColor.WHITE));
        System.out.println("White rook repositioned: " + hasPiece(game.getBoard(), new Position(7, 5), PieceType.ROOK, PieceColor.WHITE));
    }

    private static void runQueenSideCastlingDemo() {
        Board board = Board.createEmptyBoard();

        board.placePiece(new Position(7, 4), new Piece(PieceType.KING, PieceColor.WHITE));
        board.placePiece(new Position(7, 0), new Piece(PieceType.ROOK, PieceColor.WHITE));
        board.placePiece(new Position(0, 4), new Piece(PieceType.KING, PieceColor.BLACK));

        Game game = createGame("G-6", board);
        game.startGame();

        boolean result = game.makeMove(
                new Position(7, 4),
                new Position(7, 2)
        );

        printResult("Queen-side castling demo", game, result);
        System.out.println("White king castled: " + hasPiece(game.getBoard(), new Position(7, 2), PieceType.KING, PieceColor.WHITE));
        System.out.println("White rook repositioned: " + hasPiece(game.getBoard(), new Position(7, 3), PieceType.ROOK, PieceColor.WHITE));
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

    private static void printPromotedPiece(Game game, Position position) {
        Piece promotedPiece = game.getBoard().getPiece(position);

        // This proves the pawn was replaced by a new queen on the promotion square.
        System.out.println("Promoted piece type: " + promotedPiece.getType());
        System.out.println("Promoted piece color: " + promotedPiece.getColor());
    }

    private static boolean hasPiece(Board board, Position position, PieceType type, PieceColor color) {
        Piece piece = board.getPiece(position);

        return piece != null &&
                piece.getType() == type &&
                piece.getColor() == color;
    }
}
