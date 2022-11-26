package milkycode.reversi.game;

public record BoardCoordinates(int row, int column) {
    public BoardCoordinates {
        if (row < 0 || row >= 8 || column < 0 || column >= 8) {
            throw new IllegalArgumentException("Both coordinates must be in bounds of [0, 8)");
        }

    }
}
