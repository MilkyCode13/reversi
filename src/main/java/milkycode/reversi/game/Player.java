package milkycode.reversi.game;

public interface Player {
    Move getMove(Board board) throws NoValidMovesException;
    String getName();
    void handleInvalidMove(IllegalMoveException exception);
}
