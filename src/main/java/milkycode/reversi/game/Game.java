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

    /**
     * @param firstPlayer  First player.
     * @param secondPlayer Second player.
     */
    protected Game(Player firstPlayer, Player secondPlayer) {
        Random random = new Random();
        if (random.nextBoolean()) {
            this.darkPlayer = firstPlayer;
            this.lightPlayer = secondPlayer;
        } else {
            this.darkPlayer = secondPlayer;
            this.lightPlayer = firstPlayer;
        }
    }

    /**
     * @return A copy of the current board.
     */
    public Board getBoard() {
        return board.copy();
    }

    /**
     * @return Whether the game has been finished.
     */
    public boolean isFinished() {
        return board.isGameFinished();
    }

    public void run() {
        started = true;
        runToPlayerMove();
        moveHistory.addLast(board.copy());
    }

    /**
     * @param move The move to make.
     */
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

    /**
     * @param moveCoordinates Coordinates of the move.
     * @throws IllegalMoveException The move is not valid.
     */
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

    /**
     * @param color The color of the player.
     * @return The player.
     */
    public Player getPlayerByColor(PlayerColor color) {
        if (color == PlayerColor.DARK) {
            return darkPlayer;
        }

        if (color == PlayerColor.LIGHT) {
            return lightPlayer;
        }

        throw new IllegalArgumentException("Cannot get a player of a none color");
    }

    /**
     * @return The color of the current player.
     */
    public PlayerColor getCurrentPlayerColor() {
        return board.getNextPlayer();
    }

    /**
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        if (!started) {
            throw new IllegalStateException("Cannot continue the game if game hasn't been started");
        }

        if (isFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }

        return getCurrentPlayerColor() == PlayerColor.DARK ? darkPlayer : lightPlayer;
    }

    /**
     * @return The score.
     */
    public GameScore getScore() {
        return board.getScore();
    }
}
