package domain.treasureMap;

import domain.InputLine.*;
import domain.Square.*;
import exceptions.*;

import java.util.*;

public class TreasureMap {
    private int horizontalSize;
    private int verticalSize;
    private Square[][] mapSquares;

    public TreasureMap(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        this.mapSquares = new Square[horizontalSize][verticalSize];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreasureMap that = (TreasureMap) o;
        return horizontalSize == that.horizontalSize && verticalSize == that.verticalSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalSize, verticalSize);
    }

    public boolean hasInLimits(Square square) {
        return square.isInLimits(this.horizontalSize, this.verticalSize);
    }

    public void populate(List<InputLine> lines) throws OutOfMapException {
        instanciateAllSquares();
        addAttributesFromInput(lines);

    }

    private void addAttributesFromInput(List<InputLine> lines) throws OutOfMapException {
        for (InputLine line : lines) {
            Optional<Square> optionalSquare = Optional.empty();
            if (line.getType() == InputLineType.MOUNTAIN) {
                optionalSquare = Optional.ofNullable(line.extractMountain());
            } else if (line.getType() == InputLineType.TREASURE) {
                optionalSquare = Optional.ofNullable(line.extractTreasure());
            }
            if (optionalSquare.isPresent()) {
                Square square = optionalSquare.get();
                if (square.isInLimits(this.horizontalSize, this.verticalSize)) {
                    this.mapSquares[square.getHorizontalValue()][square.getVerticalValue()] = square;
                } else {
                    throw new OutOfMapException();
                }
            }
        }
    }


    private void instanciateAllSquares() {
        Square[][] squares = this.mapSquares;
        for (int i = 0, squaresLength = squares.length; i < squaresLength; i++) {
            Square[] horizontalSquareLine = squares[i];
            for (int j = 0, horizontalSquareLineLength = horizontalSquareLine.length; j < horizontalSquareLineLength; j++) {
                this.mapSquares[i][j] = new SquareBuilder().createSquare();
            }
        }
    }

    public Square getSquare(int horizontalValue, int verticalValue) {
        return this.mapSquares[horizontalValue][verticalValue];
    }
}
