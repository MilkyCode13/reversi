package milkycode.reversi.game;

import java.util.List;

public record Move(BoardCoordinates coordinates, List<BoardCoordinates> enclosedCoordinates) {

}
