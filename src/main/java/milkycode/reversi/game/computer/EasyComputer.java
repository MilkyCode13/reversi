package milkycode.reversi.game.computer;

import milkycode.reversi.BestMoveCalculator;
import milkycode.reversi.game.objects.Board;
import milkycode.reversi.game.objects.Move;

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
