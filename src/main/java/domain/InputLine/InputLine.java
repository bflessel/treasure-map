package domain.InputLine;

import domain.treasureMap.*;

import java.util.*;

public class InputLine {
    private String input;
    private InputLineType type;

    public InputLine(String input, InputLineType type) {
        this.input = input;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InputLine inputLine = (InputLine) o;
        return Objects.equals(input, inputLine.input) && type == inputLine.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, type);
    }

    public TreasureMap extractMap() {
        if (this.type == InputLineType.MAP) {
            String[] splitedInput = input.split(" - ");
            return new TreasureMapBuilder().setHorizontalValue(Integer.parseInt(splitedInput[1])).setVerticalValue(Integer.parseInt(splitedInput[2])).createTreasureMap();
        }
        return null;
    }
}
