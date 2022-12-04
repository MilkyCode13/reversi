package milkycode.reversi;

import milkycode.reversi.game.Game;
import milkycode.reversi.game.MultiPlayerGame;
import milkycode.reversi.game.SinglePlayerGame;
import milkycode.reversi.game.computer.EasyComputer;
import milkycode.reversi.game.computer.HardComputer;
import milkycode.reversi.game.exceptions.IllegalMoveException;
import milkycode.reversi.game.objects.BoardCoordinates;
import milkycode.reversi.io.Console;

import java.util.Optional;

public class Main {
    private static final Console console = new Console();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            switch (console.getMenuEntry()) {
                case EXIT:
                    exit = true;
                    break;
                case GAME_EASY:
                    runGame(new SinglePlayerGame(new EasyComputer()));
                    break;
                case GAME_HARD:
                    runGame(new SinglePlayerGame(new HardComputer()));
                    break;
                case GAME_MULTIPLAYER:
                    runGame(new MultiPlayerGame());
                    break;
                case BEST_SCORE:
                    // TODO
                    break;
            }
        }

        System.out.println("Bye!");
    }

    private static void runGame(Game game) {
        game.run();

        while (!game.isFinished()) {
            console.displayBoard(game.getBoard());
            Optional<BoardCoordinates> coordinates = console.getMove(game.getCurrentPlayer(), game.getCurrentPlayerColor());
            if (coordinates.isEmpty()) {
                try {
                    game.cancelMove();
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            try {
                game.makePlayerMove(coordinates.get());
            } catch (IllegalMoveException e) {
                System.out.println(e.getMessage());
            }
        }

        console.displayGameResults(game);
    }
}