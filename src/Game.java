import Enums.GameStatus;
import Enums.PieceColor;
import Enums.PieceType;

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

    // Castling state
    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean whiteKingSideRookMoved;
    private boolean whiteQueenSideRookMoved;
    private boolean blackKingSideRookMoved;
    private boolean blackQueenSideRookMoved;

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

        // Castling is a special king move.
        // Normal king movement allows only one square, so castling must be handled before MoveValidator.
        if(isKingSideCastleRequest(from, to)){
            return castleKingSide();
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

        // Promotion is checked only after the move is accepted.
        // If the move had left own king in check, we would have already rolled it back.
        handlePromotion(move);

        // Castling state is updated only after the move is accepted.
        // If an illegal move was rolled back, king/rook moved flags should not change.
        updateCastlingState(move);

        // track the move history
        moveHistory.add(move);

        updateGameStatusAfterAcceptedMove();

        return true;
    }

    private void handlePromotion(Move move) {
        // Promotion is not a movement rule.
        // Movement decides whether a pawn can reach the last row.
        // Promotion decides what happens after the pawn reaches that row.
        Piece movedPiece = move.getMovedPiece();

        // Only pawns can promote.
        if(movedPiece.getType() != PieceType.PAWN){
            return;
        }

        // White pawns promote on row 0.
        // Black pawns promote on row 7.
        int promotionRow = movedPiece.getColor() == PieceColor.WHITE ? 0 : 7;

        // For now, we keep promotion simple and always promote to queen.
        // Later, this can become a player choice: queen, rook, bishop, or knight.
        if(move.getTo().getRow() == promotionRow){
            board.replacePiece(move.getTo(),
                    new Piece(PieceType.QUEEN, movedPiece.getColor()));
        }
    }

    private void switchTurn(){
        currentTurn = currentTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }

    private boolean isKingSideCastleRequest(Position from, Position to){
        // Normal king move is only one square.
        // If king tries to move two columns to the right, we treat it as a king-side castling request.
        // This check only detects the request; it does not decide whether castling is allowed.
        Piece piece = board.getPiece(from);

        return piece!=null &&
                piece.getType() == PieceType.KING &&
                piece.getColor() == currentTurn &&
                from.getRow() == to.getRow() &&
                to.getCol() - from.getCol() == 2;
    }

    private boolean canCastleKingSide(PieceColor color){
        // King-side castling uses the right-side rook.
        // White uses row 7; black uses row 0.
        int row = color == PieceColor.WHITE ? 7 : 0;

        // Castling is not allowed if the king or that side's rook moved earlier.
        // Even if they came back to the original square, these flags stay true.
        boolean kingMoved = color == PieceColor.WHITE ? whiteKingMoved : blackKingMoved;
        boolean rookMoved = color == PieceColor.WHITE ? whiteKingSideRookMoved : blackKingSideRookMoved;

        // Starting positions for king-side castling.
        // King starts at column 4 and rook starts at column 7.
        // Squares at columns 5 and 6 must be empty.
        Position kingPosition = new Position(row, 4);
        Position rookPosition = new Position(row, 7);
        Position betweenOne = new Position(row, 5);
        Position betweenTwo = new Position(row, 6);

        if(kingMoved || rookMoved){
            return false;
        }

        // Make sure the expected king is still on the original king square.
        Piece king = board.getPiece(kingPosition);
        if(king == null || king.getType() != PieceType.KING || king.getColor() != color){
            return false;
        }

        // Make sure the expected rook is still on the king-side rook square.
        Piece rook = board.getPiece(rookPosition);
        if(rook == null || rook.getType() != PieceType.ROOK || rook.getColor() != color){
            return false;
        }

        // King and rook should have a clear path between them.
        if(!board.isPositionEmpty(betweenOne) || !board.isPositionEmpty(betweenTwo)){
            return false;
        }

        // A king cannot castle while it is already in check.
        if(checkDetector.isInCheck(board, color)){
            return false;
        }

        // king should not pass through check
        if(!isSquareSafeForKing(color, kingPosition, betweenOne)){
            return false;
        }
        //king should not land in check
        if(!isSquareSafeForKing(color, kingPosition, betweenTwo)){
            return false;
        }
        return true;
    }

    private boolean isSquareSafeForKing(PieceColor color, Position from, Position to) {
        Move move = board.movePiece(from, to);
        boolean inCheck = checkDetector.isInCheck(board, color);
        board.undoMove(move);
        return !inCheck;
    }

    private boolean castleKingSide() {
        // If any castling rule fails, the move is rejected.
        if(!canCastleKingSide(currentTurn)){
            return false;
        }

        int row = currentTurn == PieceColor.WHITE ? 7 : 0;

        Position kingFrom = new Position(row, 4);
        Position kingTo = new Position(row, 6);
        Position rookFrom = new Position(row, 7);
        Position rookTo = new Position(row, 5);

        // Castling moves two pieces:
        // 1. king moves two squares to the right
        // 2. rook moves to the square immediately left of the king
        Move kingMove = board.movePiece(kingFrom, kingTo);
        Move rookMove = board.movePiece(rookFrom, rookTo);

        // Both king and rook have now moved, so castling state should remember that.
        updateCastlingState(kingMove);
        updateCastlingState(rookMove);

        // For now, history stores the king move as the main castling move.
        // Later, Command pattern can model castling as one compound move.
        moveHistory.add(kingMove);

        updateGameStatusAfterAcceptedMove();
        return true;
    }

    private void updateGameStatusAfterAcceptedMove() {
        // After current player makes an accepted move, evaluate the opponent.
        // If opponent has no legal move, the game may become checkmate or stalemate.
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
    }

    private boolean canCastleQueenSide(PieceColor color){
        // Not implemented yet.
        // Queen-side castling has the same idea as king-side castling,
        // but the king moves two columns to the left.
        return false;
    }

    private void updateCastlingState(Move move){
        // Castling depends on whether the king or original rook has ever moved.
        // Even if the rook comes back to its original square later, castling is still not allowed.
        Piece movedPiece = move.getMovedPiece();
        Position from = move.getFrom();

        // If a king moves even once, that color can never castle again.
        if(movedPiece.getType() == PieceType.KING){
            if(movedPiece.getColor() == PieceColor.WHITE){
                whiteKingMoved = true;
            }
            else{
                blackKingMoved = true;
            }
            return;
        }

        // Only king and rook movement affects castling state.
        if(movedPiece.getType() != PieceType.ROOK){
            return;
        }

        // For rooks, we care about the starting square.
        // A rook moving from its original square permanently disables that side's castling.
        if(movedPiece.getColor() == PieceColor.WHITE){
            if(from.equals(new Position(7,0))){
                whiteQueenSideRookMoved = true;
            }
            else if(from.equals(new Position(7,7))){
                whiteKingSideRookMoved = true;
            }
        }
        else{
            if(from.equals(new Position(0, 0))){
                blackQueenSideRookMoved = true;
            } else if(from.equals(new Position(0, 7))){
                blackKingSideRookMoved = true;
            }
        }

    }






    // Getters
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
