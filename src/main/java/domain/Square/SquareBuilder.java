package domain.Square;

import domain.adventurer.*;

public class SquareBuilder {
    private int horizontalValue;
    private int verticalValue;
    private boolean isMountain;
    private boolean isTreasure;
    private int treasureNumber;
    private Adventurer adventurer;

    public SquareBuilder setHorizontalValue(int horizontalValue) {
        this.horizontalValue = horizontalValue;
        return this;
    }

    public SquareBuilder setVerticalValue(int verticalValue) {
        this.verticalValue = verticalValue;
        return this;
    }

    public SquareBuilder setIsMountain(boolean isMountain) {
        this.isMountain = isMountain;
        return this;
    }

    public Square createSquare() {
        return new Square(horizontalValue, verticalValue, isMountain, isTreasure, treasureNumber, adventurer);
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