package domain.square;

import domain.adventurer.*;
import domain.coordinate.*;

public class SquareBuilder {
    private boolean isMountain;
    private boolean isTreasure;
    private int treasureNumber;
    private Adventurer adventurer;
    private Coordinate coordinate;

    public SquareBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }


    public SquareBuilder setIsMountain(boolean isMountain) {
        this.isMountain = isMountain;
        return this;
    }

    public Square createSquare() {
        return new Square(coordinate, isMountain, isTreasure, treasureNumber, adventurer);
    }

    public SquareBuilder setIsTreasure(boolean isTreasure) {
        this.isTreasure = isTreasure;
        return this;
    }

    public SquareBuilder setTreasureNumber(int treasureNumber) {
        this.treasureNumber = treasureNumber;
        return this;
    }

    public SquareBuilder setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
        return this;
    }
}