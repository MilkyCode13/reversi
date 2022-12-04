package milkycode.reversi.game.objects;

/**
 * @param move  The best move.
 * @param score Score of the best move.
 */
public record BestMove(Move move, double score) {
}
