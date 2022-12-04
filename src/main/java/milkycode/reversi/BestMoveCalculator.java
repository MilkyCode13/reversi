package milkycode.reversi;

import milkycode.reversi.game.objects.BestMove;
import milkycode.reversi.game.objects.Board;
import milkycode.reversi.game.objects.BoardCoordinates;
import milkycode.reversi.game.objects.Move;

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
            board = board.copy();
            board.makeMove(move);
            score -= getBestMove(board, depth - 1).score();
        }

        return score;
    }
}
