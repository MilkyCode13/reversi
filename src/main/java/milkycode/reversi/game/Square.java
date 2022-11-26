package milkycode.reversi.game;

public enum Square {
    EMPTY,
    DARK,
    LIGHT;

    public static Square getSquare(PlayerColor color) {
        return color == PlayerColor.DARK ? DARK : LIGHT;
    }
}
