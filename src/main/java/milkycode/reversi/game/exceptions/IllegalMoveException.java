package milkycode.reversi.game.exceptions;

public class IllegalMoveException extends GameException {
    public IllegalMoveException() {
        super("The move is illegal");
    }

    public IllegalMoveException(String message) {
        super(message);
    }
}
