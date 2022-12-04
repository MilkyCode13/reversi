package milkycode.reversi.game.objects;

/**
 * @param row    The vertical coordinate.
 * @param column The horizontal coordinate.
 */
public record BoardCoordinates(int row, int column) {
    public BoardCoordinates {
        if (row < 0 || row >= 8 || column < 0 || column >= 8) {
            throw new IllegalArgumentException("Both coordinates must be in bounds of [0, 8)");
        }
    }

    /**
     * @return Whether the coordinates are in the corner.
     */
    public boolean isCorner() {
        return (row == 0 || row == 7) && (column == 0 || column == 7);
    }

    /**
     * @return Whether the coordinates are on the edge.
     */
    public boolean isEdge() {
        return (row == 0 || row == 7) || (column == 0 || column == 7);
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString() {
        return Character.toString(column + 'a') + (row + 1);
    }
}
