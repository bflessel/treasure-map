package domain.treasureMap;

import java.util.*;

public class TreasureMap {
    private int horizontalValue;
    private int verticalValue;

    public TreasureMap(int horizontalValue, int verticalValue) {
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;
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
        return horizontalValue == that.horizontalValue && verticalValue == that.verticalValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalValue, verticalValue);
    }
}
