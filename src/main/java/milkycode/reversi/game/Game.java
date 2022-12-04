package milkycode.reversi.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private final List<Board> moveHistory = new ArrayList<>();
    private Board currentBoard = new Board();
    private boolean started = false;

    public Game() {
        moveHistory.add(currentBoard.copy());
    }

    public Board getBoard() {
        return currentBoard;
    }

    public boolean isFinished() {
        return getBoard().isGameFinished();
    }

    public void run() {
        started = true;
        runToPlayerMove();
    }

    protected void makeMove(Move move) {
        currentBoard.makeMove(move);
        moveHistory.add(currentBoard.copy());
    }

    public void cancelMove() {
        if (moveHistory.size() <= 1) {
            throw new IllegalStateException("Cannot cancel the move if no moved were made");
        }
    }

    public void makePlayerMove(BoardCoordinates moveCoordinates) throws IllegalMoveException {
        if (getBoard().isGameFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }

        Move move = currentBoard.getMove(moveCoordinates);
        currentBoard.makeMove(move);

        runToPlayerMove();
    }

    protected void runToPlayerMove() {

    }
}
