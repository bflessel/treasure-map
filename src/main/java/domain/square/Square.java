package domain.square;

import domain.adventurer.*;

import java.util.*;


public class Square {
    private int horizontalValue;
    private int verticalValue;
    private Boolean isMountain;
    private Boolean isTreasure;
    private TreasureNumber treasureNumber;
    private Adventurer adventurer;

    public Square(int horizontalValue, int verticalValue, boolean isMountain, boolean isTreasure, int treasureNumber, Adventurer adventurer) {
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;

        this.isMountain = isMountain;
        this.isTreasure = isTreasure;
        this.treasureNumber = new TreasureNumber(treasureNumber);
        this.adventurer = adventurer;
    }

    public boolean isInLimits(int horizontalSize, int verticalSize) {
        return this.horizontalValue < horizontalSize &&
                this.verticalValue < verticalSize
                && this.horizontalValue>=0
                &&this.verticalValue >=0;
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
        return treasureNumber.giveNumber();
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void loseTreasure() {
        treasureNumber.loseTreasure();
    }

    @Override
    public String toString() {
        return "Square{" +
                "horizontalValue=" + horizontalValue +
                ", verticalValue=" + verticalValue +
                ", isMountain=" + isMountain +
                ", isTreasure=" + isTreasure +
                ", treasureNumber=" + treasureNumber +
                ", adventurer=" + adventurer +
                '}';
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
        return horizontalValue == square.horizontalValue && verticalValue == square.verticalValue && Objects.equals(isMountain, square.isMountain) && Objects.equals(isTreasure, square.isTreasure) && Objects.equals(treasureNumber, square.treasureNumber) && Objects.equals(adventurer, square.adventurer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalValue, verticalValue, isMountain, isTreasure, treasureNumber, adventurer);
    }
}
