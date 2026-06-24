public class Board {

    private static final int BOARD_SIZE = 3;
    private Cell[][] grid = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
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
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
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

    private boolean matches(
            int row,
            int col,
            Player player
    ) {
        return grid[row][col].isOccupied()
                && grid[row][col].getPlayer()
                .equals(player);
    }

    public boolean hasWinner(Player player) {
        // Row check
        for(int row = 0; row < BOARD_SIZE; row++) {
            boolean rowMatch = true;
            for(int col = 0; col < BOARD_SIZE; col++) {
                rowMatch = rowMatch && matches(row, col, player);
            }
            if(rowMatch) {
                return true;
            }
        }

        // Column check
        for(int col = 0; col < BOARD_SIZE; col++) {
            boolean colMatch = true;
            for(int row = 0; row < BOARD_SIZE; row++) {
                colMatch = colMatch && matches(row, col, player);
            }
            if(colMatch) {
                return true;
            }
        }

        // Primary diagonal check
        boolean primaryDiagonalMatch = matches(0, 0, player)
                && matches(1, 1, player)
                && matches(2, 2, player);
        if(primaryDiagonalMatch) {
            return true;
        }

        // Secondary diagonal check
        boolean secondaryDiagonalMatch = matches(0, 2, player)
                && matches(1, 1, player)
                && matches(2, 0, player);
        if(secondaryDiagonalMatch) {
            return true;
        }

        // No winner
        return false;

    }

    public boolean isBoardFull() {
        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                if(!grid[row][col].isOccupied()) {
                    // There is an unoccupied cell
                    return false;
                }
            }
        }
        // All cells are occupied
        return true;
    }

}
