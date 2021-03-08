package domain.adventurer;

import java.util.*;

public class Adventurer {
    private final String name;
    private final int horizontalValue;
    private final int verticalValue;
    private final Orientation orientation;
    private final String moveSet;

    public Adventurer(String name, int horizontalValue, int verticalValue, Orientation orientation, String moveSet) {
        this.name = name;
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;
        this.orientation = orientation;
        this.moveSet = moveSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Adventurer that = (Adventurer) o;
        return horizontalValue == that.horizontalValue && verticalValue == that.verticalValue && Objects.equals(name, that.name) && orientation == that.orientation && Objects.equals(moveSet, that.moveSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, horizontalValue, verticalValue, orientation, moveSet);
    }

    public int getHorizontalValue() {
        return horizontalValue;
    }

    public int getVerticalValue() {
        return verticalValue;
    }
}
