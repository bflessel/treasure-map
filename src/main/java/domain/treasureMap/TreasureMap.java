package domain.treasureMap;

import domain.adventurer.*;
import domain.coordinate.*;
import domain.inputLine.*;
import domain.square.*;
import exceptions.*;
import services.*;

import java.util.*;

public class TreasureMap {
    private final int horizontalSize;
    private final int verticalSize;
    private final Square[][] mapSquares;
    private final Adventurers adventurers;

    public TreasureMap(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        this.mapSquares = new Square[horizontalSize][verticalSize];
        this.adventurers = new Adventurers();
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

    public void populate(List<InputLine> lines) throws OutOfMapException, WrongAdventurerPlaceException, AdventurerUnknownActionException {
        instantiateAllSquares();
        addAttributesFromInput(lines);
        addAdventurers(lines);


    }

    private void addAdventurers(List<InputLine> lines) throws WrongAdventurerPlaceException, AdventurerUnknownActionException {
        for (InputLine line : lines) {
            if (line.getType() == InputLineType.ADVENTURER) {
                Adventurer adventurer = line.extractAdventurer();
                this.adventurers.add(adventurer);
                Square adventurerSquare = new SquareBuilder().setCoordinate(adventurer.getCoordinate()).setAdventurer(adventurer).createSquare();
                if (canPlaceAdventurer(adventurerSquare)) {
                    this.mapSquares[adventurerSquare.getHorizontalValue()][adventurerSquare.getVerticalValue()] = adventurerSquare;
                } else {
                    throw new WrongAdventurerPlaceException();
                }

            }
        }
    }

    private boolean canPlaceAdventurer(Square adventurerSquare) {
        return this.mapSquares[adventurerSquare.getHorizontalValue()][adventurerSquare.getVerticalValue()].hasNoAdventurer()
                && this.mapSquares[adventurerSquare.getHorizontalValue()][adventurerSquare.getVerticalValue()].isNotMountain();

    }

    private void addAttributesFromInput(List<InputLine> lines) throws OutOfMapException {
        for (InputLine line : lines) {
            Optional<Square> optionalSquare = switch (line.getType()) {
                case MOUNTAIN -> Optional.ofNullable(line.extractMountain());
                case TREASURE -> Optional.ofNullable(line.extractTreasure());
                default -> Optional.empty();
            };
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


    private void instantiateAllSquares() {
        Square[][] squares = this.mapSquares;
        for (int i = 0, squaresLength = squares.length; i < squaresLength; i++) {
            Square[] horizontalSquareLine = squares[i];
            for (int j = 0, horizontalSquareLineLength = horizontalSquareLine.length; j < horizontalSquareLineLength; j++) {
                this.mapSquares[i][j] = new SquareBuilder().createSquare();
            }
        }
    }

    public Square getSquare(Coordinate coordinate) {
        return this.mapSquares[coordinate.getHorizontalValue()][coordinate.getVerticalValue()];
    }

    public void moveAdventurer(Adventurer adventurer, Coordinate nextCoordinate) throws AdventurerUnknownActionException {
        if (canMove(nextCoordinate)) {
            int verticalValue = nextCoordinate.getVerticalValue();
            int horizontalValue = nextCoordinate.getHorizontalValue();
            int index = this.adventurers.indexOf(adventurer);
            if (hasTreasure(this.mapSquares[horizontalValue][verticalValue])) {
                adventurer.addTreasure();
                this.mapSquares[horizontalValue][verticalValue].loseTreasure();
            }
            this.mapSquares[adventurer.getHorizontalValue()][adventurer.getVerticalValue()] = this.mapSquares[adventurer.getHorizontalValue()][adventurer.getVerticalValue()].setAdventurer(null);
            Adventurer movedAdventurer = adventurer.getMovedAdventurer(nextCoordinate);
            this.mapSquares[horizontalValue][verticalValue] = this.mapSquares[horizontalValue][verticalValue].setAdventurer(movedAdventurer);
            this.adventurers.set(index, movedAdventurer);
        } else {
            AdventurerManager.missTurn(this, adventurer);
        }
    }

    private boolean hasTreasure(Square square) {
        return square.isTreasure() &&
                square.getTreasureNumber() > 0;
    }

    private boolean canMove(Coordinate nextSquare) {
        return nextSquare.isInLimits(this.horizontalSize, this.verticalSize)
                && this.mapSquares[nextSquare.getHorizontalValue()][nextSquare.getVerticalValue()].isNotMountain()
                && this.mapSquares[nextSquare.getHorizontalValue()][nextSquare.getVerticalValue()].hasNoAdventurer();

    }


    public void updateAdventurer(Adventurer adventurer, int index) {
        this.mapSquares[adventurer.getHorizontalValue()][adventurer.getVerticalValue()] = this.mapSquares[adventurer.getHorizontalValue()][adventurer.getVerticalValue()].setAdventurer(adventurer);
        this.adventurers.set(index, adventurer);
    }

    public boolean hasActions() {
        return this.adventurers.haveActions();
    }

    public int getAdventurerIndex(Adventurer adventurer) {
        return this.adventurers.indexOf(adventurer);
    }

    public void playAdventurers() throws AdventurerUnknownActionException {
        adventurers.playAll(this);
    }

    public String getAdventurerInputs() {
        return adventurers.getInput();
    }
}
