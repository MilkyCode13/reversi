package milkycode.reversi.game.computer;

import milkycode.reversi.game.objects.Board;
import milkycode.reversi.game.objects.Move;

public class HardComputer implements Computer {
    /**
     * @param board The board to get move on.
     * @return The move made by the computer.
     */
    @Override
    public Move getMove(Board board) {
        Move bestMove = BestMoveCalculator.getBestMove(board, 1).move();
        if (bestMove == null) {
            throw new IllegalStateException("Cannot get any valid moves");
        }

        return bestMove;
    }
}
