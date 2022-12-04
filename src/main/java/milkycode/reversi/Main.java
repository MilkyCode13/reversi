package milkycode.reversi;

import milkycode.reversi.game.*;
import milkycode.reversi.io.Console;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        Game game = new SinglePlayerGame(new EasyComputer());
        game.run();

        while (!game.isFinished()) {
            console.displayBoard(game.getBoard());
            BoardCoordinates coordinates = console.getMove(game.getBoard().getNextPlayer());
            try {
                game.makePlayerMove(coordinates);
            } catch (IllegalMoveException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}