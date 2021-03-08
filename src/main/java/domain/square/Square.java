package domain.square;

import domain.adventurer.*;

import java.util.*;

public class Square {
    private int horizontalValue;
    private int verticalValue;
    private boolean isMountain;
    private boolean isTreasure;
    private int treasureNumber;
    private Adventurer adventurer;

    public Square(int horizontalValue, int verticalValue, boolean isMountain, boolean isTreasure, int treasureNumber, Adventurer adventurer) {
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;

        this.isMountain = isMountain;
        this.isTreasure = isTreasure;
        this.treasureNumber = treasureNumber;
        this.adventurer = adventurer;
    }

    public boolean isInLimits(int horizontalSize, int verticalSize) {
        return this.horizontalValue < horizontalSize &&
                this.verticalValue < verticalSize
                && this.horizontalValue>=0
                &&this.verticalValue >=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return horizontalValue == square.horizontalValue && verticalValue == square.verticalValue && isMountain == square.isMountain && isTreasure == square.isTreasure && treasureNumber == square.treasureNumber && Objects.equals(adventurer, square.adventurer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalValue, verticalValue, isMountain, isTreasure, treasureNumber, adventurer);
    }

    public int getHorizontalValue() {
        return this.horizontalValue;
    }

    public int getVerticalValue() {
        return this.verticalValue;
    }

    public Adventurer getAdventurer() {
        return this.adventurer;
    }

    public boolean isNotMountain() {
        return !isMountain;
    }

    public boolean hasNoAdventurer() {
        return this.adventurer == null;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    public int getTreasureNumber() {
        return treasureNumber;
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void loseATreasure() {
        treasureNumber--;
    }

}
