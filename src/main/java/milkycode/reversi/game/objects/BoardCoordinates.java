package milkycode.reversi.game.objects;

public record BoardCoordinates(int row, int column) {
    public BoardCoordinates {
        if (row < 0 || row >= 8 || column < 0 || column >= 8) {
            throw new IllegalArgumentException("Both coordinates must be in bounds of [0, 8)");
        }
    }

    public boolean isCorner() {
        return (row == 0 || row == 7) && (column == 0 || column == 7);
    }

    public boolean isEdge() {
        return (row == 0 || row == 7) || (column == 0 || column == 7);
    }

    @Override
    public String toString() {
        return Character.toString(column + 'a') + (row + 1);
    }
}
