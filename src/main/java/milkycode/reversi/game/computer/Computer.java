package milkycode.reversi.game.computer;

import milkycode.reversi.game.objects.Board;
import milkycode.reversi.game.objects.Move;

public interface Computer {
    Move getMove(Board board);
}
