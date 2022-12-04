package milkycode.reversi.game.objects;

public enum PlayerColor {
    NONE(Square.EMPTY),
    DARK(Square.DARK),
    LIGHT(Square.LIGHT);

    private final Square square;

    PlayerColor(Square square) {
        this.square = square;
    }

    /**
     * @return Opposite color.
     */
    public PlayerColor getOther() {
        return this == DARK ? LIGHT : DARK;
    }

    /**
     * @return Respective square type.
     */
    public Square getSquare() {
        return square;
    }
}
