package milkycode.reversi.game.objects;

import java.util.List;

/**
 * @param coordinates         Coordinates of move.
 * @param enclosedCoordinates List of coordinates of all enclosed squares.
 */
public record Move(BoardCoordinates coordinates, List<BoardCoordinates> enclosedCoordinates) {
    @Override
    public String toString() {
        return coordinates.toString();
    }
}
