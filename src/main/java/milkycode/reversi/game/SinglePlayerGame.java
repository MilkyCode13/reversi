package milkycode.reversi.game;

import java.util.Random;

public class SinglePlayerGame extends Game {
    private final Computer computer;
    PlayerColor computerColor;

    public SinglePlayerGame(Computer computer) {
        this.computer = computer;

        Random random = new Random();
        computerColor = random.nextBoolean() ? PlayerColor.DARK : PlayerColor.LIGHT;
    }

    @Override
    protected void runToPlayerMove() {
        if (getBoard().isGameFinished()) {
            throw new IllegalStateException("Cannot continue the game if it has been finished");
        }

        while (getBoard().getNextPlayer() == computerColor) {
            Move move = computer.getMove(getBoard());
            makeMove(move);
        }
    }
}
