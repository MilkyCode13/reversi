package milkycode.reversi.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final Square[][] squares;
    private final PlayerColor nextPlayer;

    public Board() {
        squares = new Square[8][8];
        for (Square[] row : squares) {
            Arrays.fill(row, Square.EMPTY);
        }

        nextPlayer = PlayerColor.DARK;
    }

    private Board(Square[][] squares, PlayerColor nextPlayer) {
        this.squares = Arrays.stream(squares).map(Square[]::clone).toArray(Square[][]::new);
        this.nextPlayer = nextPlayer;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Square getSquare(BoardCoordinates coordinates) {
        return squares[coordinates.row()][coordinates.column()];
    }

    private void setSquare(BoardCoordinates coordinates, Square value) {
        squares[coordinates.row()][coordinates.column()] = value;
    }

    public PlayerColor getNextPlayer() {
        return nextPlayer;
    }

    public Board makeMove(BoardCoordinates coordinates) throws IllegalMoveException {
        if (squares[coordinates.row()][coordinates.column()] != Square.EMPTY) {
            throw new IllegalMoveException("Cannot move to occupied square");
        }

        PlayerColor currentPlayer = this.nextPlayer;
        PlayerColor nextPlayer = PlayerColor.getOther(currentPlayer);

        Board newBoard = new Board(squares, nextPlayer);

        boolean foundAny = false;
        for (Direction direction : Direction.values()) {
            if (makeMoveDirection(nextPlayer, coordinates, newBoard, direction)) {
                foundAny = true;
            }
        }

        if (!foundAny) {
            throw new IllegalMoveException("No valid enclosures found");
        }

        Square playerSquare = Square.getSquare(currentPlayer);
        newBoard.setSquare(coordinates, playerSquare);

        return newBoard;
    }

    private static boolean makeMoveDirection(PlayerColor currentPlayer, BoardCoordinates coordinates, Board newBoard, Direction direction) {
        PlayerColor nextPlayer = PlayerColor.getOther(currentPlayer);

        Square playerSquare = Square.getSquare(currentPlayer);
        Square otherSquare = Square.getSquare(nextPlayer);

        BoardCoordinates newCoordinates = coordinates;
        List<BoardCoordinates> enclosedCoordinates = new ArrayList<>();

        try {
            do {
                newCoordinates = direction.apply(newCoordinates);
                enclosedCoordinates.add(newCoordinates);
            } while (newBoard.getSquare(newCoordinates) == otherSquare);
        } catch (IllegalArgumentException exception) {
            return false;
        }

        if (newBoard.getSquare(newCoordinates) != playerSquare) {
            return false;
        }

        newBoard.setSquare(coordinates, playerSquare);
        for (BoardCoordinates squareCoordinates : enclosedCoordinates) {
            newBoard.setSquare(squareCoordinates, playerSquare);
        }
        return true;
    }
}
