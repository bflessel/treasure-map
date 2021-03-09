package domain.square;

import domain.adventurer.*;
import domain.coordinate.*;

import java.util.*;


public class Square {
    private Coordinate coordinate;
    private Boolean isMountain;
    private Boolean isTreasure;
    private TreasureNumber treasureNumber;
    private Adventurer adventurer;

    public Square(Coordinate coordinate, boolean isMountain, boolean isTreasure, int treasureNumber, Adventurer adventurer) {
        this.coordinate = coordinate;
        this.isMountain = isMountain;
        this.isTreasure = isTreasure;
        this.treasureNumber = new TreasureNumber(treasureNumber);
        this.adventurer = adventurer;
    }

    public boolean isInLimits(int horizontalSize, int verticalSize) {
        return coordinate.isInLimits(horizontalSize,verticalSize);
    }


    public int getHorizontalValue() {
        return this.coordinate.getHorizontalValue();
    }

    public int getVerticalValue() {
        return this.coordinate.getVerticalValue();
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
        return treasureNumber.giveNumber();
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void loseTreasure() {
        treasureNumber.loseTreasure();
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
        return Objects.equals(coordinate, square.coordinate) && Objects.equals(isMountain, square.isMountain) && Objects.equals(isTreasure, square.isTreasure) && Objects.equals(treasureNumber, square.treasureNumber) && Objects.equals(adventurer, square.adventurer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, isMountain, isTreasure, treasureNumber, adventurer);
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
