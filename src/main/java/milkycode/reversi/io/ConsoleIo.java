package milkycode.reversi.io;

import milkycode.reversi.game.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleIo implements GameIo {
    private final Scanner in = new Scanner(System.in);
    private final Pattern movePattern = Pattern.compile("^\\s*([a-h])([1-8])\\s*$");

    @Override
    public void displayBoard(Board board) {
        Square[][] squares = board.getSquares();

        for (Square[] row : squares) {
            for (Square square : row) {
                switch (square) {
                    case DARK -> System.out.print("● ");
                    case LIGHT -> System.out.print("◯ ");
                    case EMPTY -> System.out.print("  ");
                }
            }

            System.out.println();
        }
    }

    @Override
    public BoardCoordinates getMove(Player player) {
        System.out.print(player.getName());
        System.out.print("> ");

        String input = in.nextLine();
        Matcher matcher = movePattern.matcher(input);

        if (!matcher.find()) {
            throw new IllegalMoveException("Hmm");
        }
    }
}
