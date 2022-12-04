package milkycode.reversi.game;

public class IllegalMoveException extends GameException {
    public IllegalMoveException() {
        super("The move is illegal");
    }
    public IllegalMoveException(String message) {
        super(message);
    }
}
