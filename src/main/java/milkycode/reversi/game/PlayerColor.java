package milkycode.reversi.game;

public enum PlayerColor {
    DARK,
    LIGHT;

    public static PlayerColor getOther(PlayerColor color) {
        return color == DARK ? LIGHT : DARK;
    }
}
