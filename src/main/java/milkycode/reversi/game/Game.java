package milkycode.reversi.game;

import milkycode.reversi.game.exceptions.IllegalMoveException;
import milkycode.reversi.game.objects.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public abstract class Game {
    private final Player darkPlayer;
    private final Player lightPlayer;

    private final Deque<Board> moveHistory = new ArrayDeque<>();
    private Board board = new Board();
    private boolean started = false;

    public Game(Player firstPlayer, Player secondPlayer) {
        Random random = new Random();
        if (random.nextBoolean()) {
            this.darkPlayer = firstPlayer;
            this.lightPlayer = secondPlayer;
        } else {
            this.darkPlayer = secondPlayer;
            this.lightPlayer = firstPlayer;
        }
    }

    public Board getBoard() {
        return board.copy();
    }

    public boolean isFinished() {
        return board.isGameFinished();
    }

    public void run() {
        started = true;
        runToPlayerMove();
        moveHistory.addLast(board.copy());
    }

    protected void makeMove(Move move) {
        board.makeMove(move);
    }

    public void cancelMove() {
        if (!started) {
            throw new IllegalStateException("Cannot cancel the move if game hasn't been started");
        }

        if (moveHistory.size() <= 1) {
            throw new IllegalStateException("Cannot cancel the move if no moves were made");
        }

        moveHistory.removeLast();
        board = moveHistory.getLast().copy();
    }

    public void makePlayerMove(BoardCoordinates moveCoordinates) throws IllegalMoveException {
        if (!started) {
            throw new IllegalStateException("Cannot continue the game if game hasn't been started");
        }

        if (isFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }

        Move move = board.getMove(moveCoordinates);
        makeMove(move);

        if (isFinished()) {
            moveHistory.add(board.copy());
            return;
        }

        runToPlayerMove();
        moveHistory.add(board.copy());
    }

    protected abstract void runToPlayerMove();

    public Player getPlayerByColor(PlayerColor color) {
        if (color == PlayerColor.DARK) {
            return darkPlayer;
        }

        if (color == PlayerColor.LIGHT) {
            return lightPlayer;
        }

        throw new IllegalArgumentException("Cannot get a player of a none color");
    }

    public PlayerColor getCurrentPlayerColor() {
        return board.getNextPlayer();
    }

    public Player getCurrentPlayer() {
        if (!started) {
            throw new IllegalStateException("Cannot continue the game if game hasn't been started");
        }

        if (isFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }

        return getCurrentPlayerColor() == PlayerColor.DARK ? darkPlayer : lightPlayer;
    }

    public GameScore getScore() {
        return board.getScore();
    }
}
