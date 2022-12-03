package milkycode.reversi.game;

public enum PlayerColor {
    DARK(Square.DARK),
    LIGHT(Square.LIGHT);

    private final Square square;

    PlayerColor(Square square) {
        this.square = square;
    }

    public PlayerColor getOther() {
        return this == DARK ? LIGHT : DARK;
    }

    public Square getSquare() {
        return square;
    }
}
