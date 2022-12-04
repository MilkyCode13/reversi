package milkycode.reversi.game;

import milkycode.reversi.BestMoveCalculator;

import java.util.List;

public class EasyComputer implements Computer {
    @Override
    public Move getMove(Board board) {
        Move bestMove = BestMoveCalculator.getBestMove(board, 0).move();
        if (bestMove == null) {
            throw new IllegalStateException("Cannot get any valid moves");
        }

        return bestMove;
    }
}
