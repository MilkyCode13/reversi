package milkycode.reversi.game.objects;

import java.util.List;

public record Move(BoardCoordinates coordinates, List<BoardCoordinates> enclosedCoordinates) {
    @Override
    public String toString() {
        return coordinates.toString();
    }
}
