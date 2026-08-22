import java.util.Objects;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        // A chessboard has valid coordinates from 0 through 7.
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            throw new IllegalArgumentException("Invalid board position");
        }
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Two positions are equal when they have the same board coordinates.
    @Override
    public boolean equals(Object obj) {
        // The same object is always equal to itself.
        if (obj == this) {
            return true;
        }

        // Objects of another type cannot represent the same position.
        if (!(obj instanceof Position)) {
            return false;
        }

        Position other = (Position) obj;
        // Compare both coordinates to identify the board square.
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        // Equal positions must produce the same hash code for map and set lookups.
        return Objects.hash(row, col);
    }
}
