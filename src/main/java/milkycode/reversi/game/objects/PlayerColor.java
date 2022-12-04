package milkycode.reversi.game.objects;

public enum PlayerColor {
    NONE(Square.EMPTY),
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
