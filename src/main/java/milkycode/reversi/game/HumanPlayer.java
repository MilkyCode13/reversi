package milkycode.reversi.game;

import milkycode.reversi.io.ConsoleIo;

public class HumanPlayer implements Player {
    private final String name;
    private final ConsoleIo io;

    public HumanPlayer(String name, ConsoleIo io) {
        this.name = name;
        this.io = io;
    }

    @Override
    public Move getMove(Board board) {
        io.displayBoard(board);
        BoardCoordinates moveCoordinates = io.getMove(this);
        // return board.getAvailableMoves().stream().filter(move -> move.coordinates().equals(moveCoordinates)).findFirst();

        // TODO: Fix this!
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void handleInvalidMove(IllegalMoveException exception) {
        io.printInvalidMove(exception.getMessage());
    }
}
