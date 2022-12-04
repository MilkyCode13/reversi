package milkycode.reversi.game.objects;

public enum Direction {
    NORTH(-1, 0),
    NORTHEAST(-1, 1),
    EAST(0, 1),
    SOUTHEAST(1, 1),
    SOUTH(1, 0),
    SOUTHWEST(1, -1),
    WEST(0, -1),
    NORTHWEST(-1, -1);

    private final int rowsDelta;
    private final int columnsDelta;


    Direction(int rowsDelta, int columnsDelta) {
        this.rowsDelta = rowsDelta;
        this.columnsDelta = columnsDelta;
    }

    public BoardCoordinates apply(BoardCoordinates coordinates) {
        return new BoardCoordinates(coordinates.row() + rowsDelta, coordinates.column() + columnsDelta);
    }
}
