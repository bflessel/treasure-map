package domain.coordinate;

import domain.adventurer.*;

import java.util.*;

public class Coordinate {
    private int horizontalValue;
    private int verticalValue;

    public Coordinate(int horizontalValue, int verticalValue) {
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;
    }

    public Coordinate getForwardSquare(Orientation orientation) {
        CoordinateBuilder builder = new CoordinateBuilder();
        switch (orientation) {
            case SOUTH -> builder
                    .setHorizontalValue(this.horizontalValue)
                    .setVerticalValue(this.verticalValue + 1);
            case NORTH -> builder
                    .setHorizontalValue(this.horizontalValue)
                    .setVerticalValue(this.verticalValue - 1);
            case WEST -> builder
                    .setHorizontalValue(this.horizontalValue - 1)
                    .setVerticalValue(this.verticalValue);
            case EAST -> builder
                    .setHorizontalValue(this.horizontalValue + 1)
                    .setVerticalValue(this.verticalValue);
        }
        return builder.createCoordinate();

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return horizontalValue == that.horizontalValue && verticalValue == that.verticalValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalValue, verticalValue);
    }

    public boolean isInLimits(int horizontalSize, int verticalSize) {
        return this.horizontalValue < horizontalSize &&
                this.verticalValue < verticalSize
                && this.horizontalValue>=0
                &&this.verticalValue >=0;
    }


    public int getHorizontalValue() {
        return horizontalValue;
    }

    public int getVerticalValue() {
        return verticalValue;
    }
}
