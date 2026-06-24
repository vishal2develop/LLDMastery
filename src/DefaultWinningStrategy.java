public class DefaultWinningStrategy implements WinningStrategy{

    private boolean matches(
            int row,
            int col,
            Player player,
            Cell[][] grid
    ) {
        return grid[row][col].isOccupied()
                && grid[row][col].getPlayer()
                .equals(player);
    }

    @Override
    public boolean hasWinner(Board board, Player player) {

        Cell[][] grid = board.getGrid();

        int BOARD_SIZE = board.getBoardSize();
        // Row check
        for(int row = 0; row < BOARD_SIZE; row++) {
            boolean rowMatch = true;
            for(int col = 0; col < BOARD_SIZE; col++) {
                rowMatch = rowMatch && matches(row, col, player,grid);
            }
            if(rowMatch) {
                return true;
            }
        }

        // Column check
        for(int col = 0; col < BOARD_SIZE; col++) {
            boolean colMatch = true;
            for(int row = 0; row < BOARD_SIZE; row++) {
                colMatch = colMatch && matches(row, col, player,grid);
            }
            if(colMatch) {
                return true;
            }
        }

        // Primary diagonal check
        boolean primaryDiagonalMatch = true;

        for (int i = 0; i < BOARD_SIZE; i++) {
            primaryDiagonalMatch &= matches(i, i, player, grid);
        }
        if(primaryDiagonalMatch) {
            return true;
        }

        // Secondary diagonal check
        boolean secondaryDiagonalMatch = true;

        for (int i = 0; i < BOARD_SIZE; i++) {
            secondaryDiagonalMatch &=
                    matches(i,
                            BOARD_SIZE - 1 - i,
                            player,
                            grid);
        }
        if(secondaryDiagonalMatch) {
            return true;
        }

        // No winner
        return false;
    }
}
