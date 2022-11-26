package milkycode.reversi.game;

public interface Player {
    BoardCoordinates makeMove(Board board);
    String getName();
}
