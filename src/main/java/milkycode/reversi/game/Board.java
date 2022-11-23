package milkycode.reversi.game;

import java.util.Arrays;

public class Board {
    private final Square[][] squares = new Square[8][8];

    public Board() {
        for (Square[] row : squares) {
            Arrays.fill(row, Square.EMPTY);
        }
    }
}
