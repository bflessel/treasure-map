package domain.square;

import java.util.*;

public class TreasureNumber {
    private int number;

    public TreasureNumber(int treasureNumber) {
        this.number = treasureNumber;
    }

    public TreasureNumber() {

    }


    public void loseTreasure() {
        number--;
    }

    public int giveNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreasureNumber that = (TreasureNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public void addTreasure() {
        number++;
    }
}

