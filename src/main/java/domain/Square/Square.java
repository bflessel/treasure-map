package domain.Square;

import java.util.*;

public class Square {
    private int horizontalValue;
    private int verticalValue;
    private boolean isMountain;

    public Square(int horizontalValue, int verticalValue, boolean isMountain) {
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;

        this.isMountain = isMountain;
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
        return horizontalValue == square.horizontalValue && verticalValue == square.verticalValue && isMountain == square.isMountain;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalValue, verticalValue, isMountain);
    }
}
