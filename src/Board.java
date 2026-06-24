public class Board {

    private final int boardSize;

    private Cell[][] grid;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.grid = new Cell[boardSize][boardSize];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void placeMove(int row, int col, Player player) {
        validateMove(row, col);
        grid[row][col].occupy(player);
    }

    public void validateMove(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (grid[row][col].isOccupied()) {
            throw new IllegalStateException("Cell is already occupied");
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    public void printBoard() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.isOccupied()) {
                    System.out.print(cell.getPlayer().getSymbol() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(!grid[row][col].isOccupied()) {
                    // There is an unoccupied cell
                    return false;
                }
            }
        }
        // All cells are occupied
        return true;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
