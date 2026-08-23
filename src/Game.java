import Enums.GameStatus;
import Enums.PieceColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final String gameId;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final Board board;
    private final MoveValidator moveValidator;
    private final CheckDetector checkDetector;
    private final LegalMoveDetector legalMoveDetector;

    private PieceColor currentTurn = PieceColor.WHITE;
    private GameStatus status = GameStatus.NOT_STARTED;
    private final List<Move> moveHistory = new ArrayList<>();

    Game(String gameId, Player whitePlayer, Player blackPlayer, Board board) {
        this.gameId = gameId;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.checkDetector = new CheckDetector();
        this.moveValidator = new MoveValidator();
        this.legalMoveDetector = new LegalMoveDetector();
    }

    public void startGame() {
        System.out.println("Game started!");
        status = GameStatus.IN_PROGRESS;
        currentTurn = PieceColor.WHITE;
    }

    public boolean makeMove(Position from, Position to) {
        // check status of the game
        if (status != GameStatus.IN_PROGRESS) {
            return false;
        }
        // if the move is invalid, return false
        if(!moveValidator.isValidMove(board, from, to, currentTurn)){
            return false;
        }

        // Make the move
        Move move = board.movePiece(from,to);

        // check if the current player's own king is in check
        if(checkDetector.isInCheck(board, currentTurn)){
            // undo the move
            board.undoMove(move);
            return false;
        }

        // track the move history
        moveHistory.add(move);

        // get opponents color
        PieceColor opponentColor = currentTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;

        boolean opponentHasKingInCheck = checkDetector.isInCheck(board, opponentColor);

        boolean opponentHasLegalMove = legalMoveDetector.hasAnyLegalMove(board, opponentColor);

        if(opponentHasKingInCheck && !opponentHasLegalMove){
            status = GameStatus.CHECKMATE;
        } else if (!opponentHasKingInCheck && !opponentHasLegalMove) {
            status = GameStatus.STALEMATE;
        }
        else{
            switchTurn();
        }

        return true;
    }

    private void switchTurn(){
        currentTurn = currentTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }


    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Move> getMoveHistory() {
        return Collections.unmodifiableList(moveHistory);
    }
}
