package milkycode.reversi.io;

import milkycode.reversi.game.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleIo {
    private final Scanner in = new Scanner(System.in);
    private final Pattern movePattern = Pattern.compile("^\\s*([a-hA-H][1-8])\\s*$");

    public void displayBoard(Board board) {
        Square[][] squares = board.getSquares();

        printColumnNames();

        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            System.out.print(" ");
            for (int j = 0; j < 8; j++) {
                switch (squares[i][j]) {
                    case DARK -> System.out.print("● ");
                    case LIGHT -> System.out.print("◯ ");
                    case EMPTY -> System.out.print("  ");
                }
            }
            System.out.print(i + 1);

            System.out.println();
        }

        printColumnNames();
    }

    private static void printColumnNames() {
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char) ('a' + i));
            System.out.print(" ");
        }
        System.out.println();
    }

    public BoardCoordinates getMove(Player player) {
        Matcher matcher;
        do {
            System.out.print(player.getName());
            System.out.print("> ");

            String input = in.nextLine();
            matcher = movePattern.matcher(input);

            if (matcher.find()) {
                break;
            }

            System.out.println("Invalid move syntax.");
        } while (true);

        String coordinates = matcher.group(1);

        return new BoardCoordinates(coordinates.charAt(1) - '1', Character.toLowerCase(coordinates.charAt(0)) - 'a');
    }

    public void printInvalidMove(String message) {
        System.out.println("Invalid move: " + message);
    }
}
