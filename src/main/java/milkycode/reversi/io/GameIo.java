package milkycode.reversi.io;

import milkycode.reversi.game.Board;
import milkycode.reversi.game.BoardCoordinates;
import milkycode.reversi.game.Player;

public interface GameIo {
    void displayBoard(Board board);

    BoardCoordinates getMove(Player player);
}
