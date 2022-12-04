package milkycode.reversi.game.computer;

import milkycode.reversi.game.objects.Board;
import milkycode.reversi.game.objects.Move;

public interface Computer {
    /**
     * @param board The board to get move on.
     * @return The move made by the computer.
     */
    Move getMove(Board board);
}
