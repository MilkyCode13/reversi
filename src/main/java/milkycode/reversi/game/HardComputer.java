package milkycode.reversi.game;

import milkycode.reversi.BestMoveCalculator;

public class HardComputer implements Computer{
    @Override
    public Move getMove(Board board) {
        Move bestMove = BestMoveCalculator.getBestMove(board, 1).move();
        if (bestMove == null) {
            throw new IllegalStateException("Cannot get any valid moves");
        }

        return bestMove;
    }
}
