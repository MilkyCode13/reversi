package milkycode.reversi;

import milkycode.reversi.game.ComputerPlayer;
import milkycode.reversi.game.Game;
import milkycode.reversi.game.HumanPlayer;
import milkycode.reversi.game.Player;
import milkycode.reversi.io.ConsoleIo;

public class Main {
    public static void main(String[] args) {
        ConsoleIo io = new ConsoleIo();
        Player first = new HumanPlayer("Player1", io);
        Player second = new ComputerPlayer();
        Game game = new Game(first, second);

        while (true) {
            game.move();
        }
    }
}