package milkycode.reversi;

import milkycode.reversi.game.Game;
import milkycode.reversi.game.MultiPlayerGame;
import milkycode.reversi.game.SinglePlayerGame;
import milkycode.reversi.game.computer.EasyComputer;
import milkycode.reversi.game.computer.HardComputer;
import milkycode.reversi.game.exceptions.IllegalMoveException;
import milkycode.reversi.game.objects.BoardCoordinates;
import milkycode.reversi.game.objects.GameScore;
import milkycode.reversi.game.objects.PlayerColor;
import milkycode.reversi.io.Console;

import java.util.Optional;

public class Main {
    private static final Console console = new Console();
    private static int bestHumanScore = 0;

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            switch (console.getMenuEntry()) {
                case EXIT -> exit = true;
                case GAME_EASY -> runGame(new SinglePlayerGame(new EasyComputer()));
                case GAME_HARD -> runGame(new SinglePlayerGame(new HardComputer()));
                case GAME_MULTIPLAYER -> runGame(new MultiPlayerGame());
                case BEST_SCORE -> console.displayBestHumanScore(bestHumanScore);
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

        GameScore score = game.getScore();

        if (!game.getPlayerByColor(PlayerColor.DARK).isComputer() && score.darkScore() > bestHumanScore) {
            bestHumanScore = score.darkScore();
        }

        if (!game.getPlayerByColor(PlayerColor.LIGHT).isComputer() && score.lightScore() > bestHumanScore) {
            bestHumanScore = score.lightScore();
        }
    }
}