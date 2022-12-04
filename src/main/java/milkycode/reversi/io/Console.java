package milkycode.reversi.io;

import milkycode.reversi.game.Game;
import milkycode.reversi.game.objects.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console {
    private final Scanner in = new Scanner(System.in);
    private final Pattern movePattern = Pattern.compile("[a-hA-H][1-8]");

    private static void printColumnNames() {
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char) ('a' + i));
            System.out.print(" ");
        }
        System.out.println();
    }

    public MenuEntry getMenuEntry() {
        System.out.println("Select menu item:");
        for (MenuEntry entry : MenuEntry.values()) {
            System.out.println(entry.ordinal() + ") " + entry.getDescription());
        }

        while (true) {
            System.out.print("> ");

            try {
                int number = in.nextInt();
                in.nextLine();

                if (number < 0 || number > MenuEntry.values().length) {
                    continue;
                }

                return MenuEntry.values()[number];
            } catch (NoSuchElementException e) {
                in.nextLine();
                System.out.println("Invalid number");
            }
        }
    }

    public void displayBoard(Board board) {
        List<Move> moves = board.getAvailableMoves();
        System.out.print("Available moves: ");
        for (Move move : moves) {
            System.out.print(move.toString() + " ");
        }
        System.out.println();

        printColumnNames();

        Square[][] squares = board.getSquaresWithAvailableMoves();
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            System.out.print(" ");
            for (int j = 0; j < 8; j++) {
                switch (squares[i][j]) {
                    case DARK -> System.out.print("● ");
                    case LIGHT -> System.out.print("◯ ");
                    case EMPTY -> System.out.print("  ");
                    case AVAILABLE_MOVE -> System.out.print("◌ ");
                }
            }
            System.out.print(i + 1);

            System.out.println();
        }

        printColumnNames();
    }

    public Optional<BoardCoordinates> getMove(Player player, PlayerColor color) {
        do {
            System.out.print(player.name() + " (" + color + "), enter move ('cancel' to cancel move)> ");

            String input = in.next();
            in.nextLine();
            if (input.equals("cancel")) {
                return Optional.empty();
            }

            Matcher matcher = movePattern.matcher(input);

            if (matcher.matches()) {
                int row = input.charAt(1) - '1';
                int column = Character.toLowerCase(input.charAt(0)) - 'a';
                return Optional.of(new BoardCoordinates(row, column));
            }

            System.out.println("Invalid move syntax.");
        } while (true);
    }

    public void displayGameResults(Game game) {
        GameScore score = game.getScore();
        PlayerColor winnerColor = score.darkScore() > score.lightScore() ? PlayerColor.DARK : PlayerColor.LIGHT;

        System.out.println(game.getPlayerByColor(winnerColor).name() + " have won! Score is " + score.darkScore() + " : " + score.lightScore());
    }

    public void displayBestHumanScore(int score) {
        System.out.println("The best score is " + score);
    }
}
