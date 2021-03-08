package domain.treasureMap;

import domain.Square.*;

import java.util.*;

public class TreasureMap {
    private int horizontalSize;
    private int verticalSize;

    public TreasureMap(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
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
}
