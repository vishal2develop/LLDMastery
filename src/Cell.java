public class Cell {
    private Player player;

    public boolean isOccupied() {
        return player != null;
    }

    public Player getPlayer() {
        return player;
    }

    public void occupy(Player player) {
        if(isOccupied()) {
            throw new IllegalStateException("Cell is already occupied");
        }
        this.player = player;
    }


}
