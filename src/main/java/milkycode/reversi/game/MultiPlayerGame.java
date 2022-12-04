package milkycode.reversi.game;

import milkycode.reversi.game.objects.Player;

public class MultiPlayerGame extends Game {
    public MultiPlayerGame() {
        super(new Player("Player 1", false), new Player("Player 2", false));
    }

    @Override
    protected void runToPlayerMove() {
        if (isFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }
    }
}
