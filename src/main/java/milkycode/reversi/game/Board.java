package milkycode.reversi.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {
    private final Square[][] squares;
    private PlayerColor nextPlayer;
    private List<Move> availableMoves;

    public Board() {
        squares = new Square[8][8];
        for (Square[] row : squares) {
            Arrays.fill(row, Square.EMPTY);
        }

        squares[3][3] = Square.LIGHT;
        squares[3][4] = Square.DARK;
        squares[4][3] = Square.DARK;
        squares[4][4] = Square.LIGHT;

        nextPlayer = PlayerColor.DARK;

        availableMoves = calculateAvailableMoves();
    }

    private Board(Square[][] squares, PlayerColor nextPlayer, List<Move> availableMoves) {
        this.squares = Arrays.stream(squares).map(Square[]::clone).toArray(Square[][]::new);
        this.nextPlayer = nextPlayer;
        this.availableMoves = new ArrayList<>(availableMoves);
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

    public Board copy() {
        return new Board(squares, nextPlayer, availableMoves);
    }

    public void makeMove(Move move) throws IllegalMoveException {
        Square playerSquare = nextPlayer.getSquare();
        setSquare(move.coordinates(), playerSquare);
        for (BoardCoordinates coordinates : move.enclosedCoordinates()) {
            setSquare(coordinates, playerSquare);
        }

        nextPlayer = nextPlayer.getOther();
        availableMoves = calculateAvailableMoves();
    }

    public List<Move> calculateAvailableMoves() {
        List<Move> availableMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BoardCoordinates coordinates = new BoardCoordinates(i, j);

                try {
                    availableMoves.add(calculateMove(coordinates));
                } catch (IllegalMoveException e) {
                    // Skip illegal moves
                }
            }
        }

        return availableMoves;
    }

    public Move calculateMove(BoardCoordinates moveCoordinates) throws IllegalMoveException {
        if (getSquare(moveCoordinates) != Square.EMPTY) {
            throw new IllegalMoveException("Cannot move to occupied square");
        }

        List<BoardCoordinates> enclosedSquares = getEnclosedSquares(moveCoordinates);

        if (enclosedSquares.isEmpty()) {
            throw new IllegalMoveException("No valid enclosures found");
        }

        return new Move(moveCoordinates, enclosedSquares);
    }

    public List<BoardCoordinates> getEnclosedSquares(BoardCoordinates moveCoordinates) {
        List<BoardCoordinates> enclosedCoordinates = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            enclosedCoordinates.addAll(getEnclosedSquaresByDirection(moveCoordinates, direction));
        }
        return enclosedCoordinates;
    }

    private List<BoardCoordinates> getEnclosedSquaresByDirection(BoardCoordinates moveCoordinates, Direction direction) {
        List<BoardCoordinates> enclosedCoordinates = new ArrayList<>();
        PlayerColor otherPlayer = nextPlayer.getOther();
        Square otherSquare = otherPlayer.getSquare();

        try {
            BoardCoordinates coordinates = direction.apply(moveCoordinates);
            while (getSquare(coordinates) == otherSquare) {
                enclosedCoordinates.add(coordinates);
                coordinates = direction.apply(coordinates);
            }

            if (getSquare(coordinates) != nextPlayer.getSquare()) {
                return Collections.emptyList();
            }
        } catch (IllegalArgumentException exception) {
            return Collections.emptyList();
        }

        return enclosedCoordinates;
    }

    public List<Move> getAvailableMoves() {
        return availableMoves;
    }
}
