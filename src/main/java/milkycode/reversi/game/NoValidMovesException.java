package milkycode.reversi.game;

public class NoValidMovesException extends GameException{
    public NoValidMovesException() {
        super("No valid moves found");
    }
}
