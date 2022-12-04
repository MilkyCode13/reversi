package milkycode.reversi;

import milkycode.reversi.game.BestMove;
import milkycode.reversi.game.Board;
import milkycode.reversi.game.BoardCoordinates;
import milkycode.reversi.game.Move;

import java.util.List;

public class BestMoveCalculator {
    public static BestMove getBestMove(Board board, int depth) {
        Move bestMove = null;
        double bestMoveScore = Double.NEGATIVE_INFINITY;

        for (Move move : board.calculateAvailableMoves()) {
            double moveScore = getMoveScore(board, move, depth);

            if (moveScore > bestMoveScore) {
                bestMove = move;
                bestMoveScore = moveScore;
            }
        }

        if (bestMove == null) {
            return new BestMove(null, 0);
        }

        return new BestMove(bestMove, bestMoveScore);
    }

    private static double getMoveScore(Board board, Move move, int depth) {
        double score = 0;

        List<BoardCoordinates> enclosedSquares = move.enclosedCoordinates();

        for (BoardCoordinates coordinates : enclosedSquares) {
            if (coordinates.isEdge()) {
                score += 2;
                continue;
            }

            score += 1;
        }

        if (move.coordinates().isCorner()) {
            return score + 0.8;
        }

        if (move.coordinates().isEdge()) {
            return score + 0.4;
        }

        if (depth > 0) {
            board.makeMove(move);
            score -= getBestMove(board, depth - 1).score();
        }

        return score;
    }
}
