import Enums.PieceColor;

public class Player {
    private final String playerId;
    private final String playerName;
    private final PieceColor playerColor;

    public Player(String playerId, String playerName, PieceColor playerColor) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    public PieceColor getPlayerColor() {
        return playerColor;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getPlayerId() {
        return playerId;
    }
}
