package milkycode.reversi.game;

import milkycode.reversi.game.computer.Computer;
import milkycode.reversi.game.objects.Move;
import milkycode.reversi.game.objects.Player;

public class SinglePlayerGame extends Game {
    private final Computer computer;

    /**
     * @param computer The computer to compete with the player.
     */
    public SinglePlayerGame(Computer computer) {
        super(new Player("Player", false), new Player("Computer", true));
        this.computer = computer;
    }

    @Override
    protected void runToPlayerMove() {
        if (isFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }

        while (isCurrentPlayerComputer()) {
            Move move = computer.getMove(getBoard());
            makeMove(move);
        }
    }

    private boolean isCurrentPlayerComputer() {
        return !isFinished() && getCurrentPlayer().isComputer();
    }
}
