public class Player {
    private String playerName;
    private Symbol symbol;

    public Player(String playerName, Symbol symbol) {
        this.playerName = playerName;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getPlayerName() {
        return playerName;
    }
}
