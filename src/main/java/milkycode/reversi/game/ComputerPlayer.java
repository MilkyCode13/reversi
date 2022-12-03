package milkycode.reversi.game;

import java.util.List;

public class ComputerPlayer implements Player {
    @Override
    public Move getMove(Board board) throws NoValidMovesException {
        Move bestMove = null;
        double bestMoveScore = Double.NEGATIVE_INFINITY;

        for (Move move : board.calculateAvailableMoves()) {
            double moveScore = getMoveScore(board, move);

            if (moveScore > bestMoveScore) {
                bestMove = move;
                bestMoveScore = moveScore;
            }
        }

        if (bestMove == null) {
            throw new NoValidMovesException();
        }

        return bestMove;
    }

    private double getMoveScore(Board board, Move move) {
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

        return score;
    }

    @Override
    public String getName() {
        return "Beep Boop";
    }

    @Override
    public void handleInvalidMove(IllegalMoveException exception) {
        throw new UnsupportedOperationException("Something bad happened");
    }
}
