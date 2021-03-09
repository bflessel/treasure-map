package domain.square;

import java.util.*;

public class TreasureNumber {
    private int treasureNumber;

    public TreasureNumber(int treasureNumber) {
        this.treasureNumber = treasureNumber;
    }

    public TreasureNumber() {

    }


    public void loseTreasure() {
        treasureNumber--;
    }

    public int giveNumber() {
        return treasureNumber;
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
        return treasureNumber == that.treasureNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(treasureNumber);
    }

    public void addTreasure() {
        treasureNumber++;
    }
}

