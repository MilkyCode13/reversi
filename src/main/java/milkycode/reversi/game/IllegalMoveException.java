package milkycode.reversi.game;

public class IllegalMoveException extends GameException {
    public IllegalMoveException(String message) {
        super(message);
    }
}
