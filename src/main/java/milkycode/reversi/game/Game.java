package milkycode.reversi.game;

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

        moveHistory.add(currentBoard);
    }

    public Board getBoard() {
        return currentBoard;
    }

    public void move() {
        Player currentPlayer = currentBoard.getNextPlayer() == PlayerColor.DARK ? darkPlayer : lightPlayer;

        Board newBoard;
        try {
            BoardCoordinates moveCoordinates = currentPlayer.makeMove(currentBoard);
            newBoard = currentBoard.makeMove(moveCoordinates);
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }

        currentBoard = newBoard;
        moveHistory.add(currentBoard);
    }
}
