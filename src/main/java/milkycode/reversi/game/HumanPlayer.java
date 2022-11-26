package milkycode.reversi.game;

import milkycode.reversi.io.GameIo;

public class HumanPlayer implements Player {
    private final String name;
    private final GameIo io;

    public HumanPlayer(String name, GameIo io) {
        this.name = name;
        this.io = io;
    }

    @Override
    public BoardCoordinates makeMove(Board board) {
    }

    @Override
    public String getName() {
        return name;
    }
}
