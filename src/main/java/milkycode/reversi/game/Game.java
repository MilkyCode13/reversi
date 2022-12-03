package milkycode.reversi.game;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final Player darkPlayer;
    private final Player lightPlayer;
    private final List<Board> moveHistory = new ArrayList<>();
    private Board currentBoard = new Board();

    public Game(Player firstPlayer, Player secondPlayer) {
        Random random = new Random();
        if (random.nextBoolean()) {
            darkPlayer = firstPlayer;
            lightPlayer = secondPlayer;
        } else {
            darkPlayer = secondPlayer;
            lightPlayer = firstPlayer;
        }

        moveHistory.add(currentBoard.copy());
    }

    public Board getBoard() {
        return currentBoard;
    }

    public void move() {
        Player currentPlayer = currentBoard.getNextPlayer() == PlayerColor.DARK ? darkPlayer : lightPlayer;

        Board newBoard;
        do {
            try {
                Move move = currentPlayer.getMove(currentBoard);
                currentBoard.makeMove(move);
                break;
            } catch (IllegalMoveException e) {
                currentPlayer.handleInvalidMove(e);
            } catch (NoValidMovesException e) {
                throw new IllegalStateException(e);
            }
        } while (true);

        moveHistory.add(currentBoard.copy());
    }
}
