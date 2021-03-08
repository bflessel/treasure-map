package domain.InputLine;

import domain.Square.*;
import domain.treasureMap.*;

import java.util.*;

public class InputLine {
    public static final String SEPARATOR = " - ";
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
            String[] splitedInput = input.split(SEPARATOR);
            return new TreasureMapBuilder().setHorizontalValue(Integer.parseInt(splitedInput[1])).setVerticalValue(Integer.parseInt(splitedInput[2])).createTreasureMap();
        }
        return null;
    }

    public Square extractMountain() {
        if (this.type == InputLineType.MOUNTAIN) {
            String[] splitedInput = input.split(SEPARATOR);
            return new SquareBuilder().setHorizontalValue(Integer.parseInt(splitedInput[1])).setVerticalValue(Integer.parseInt(splitedInput[2])).setIsMountain(true).createSquare();
        }
        return null;    }

    public Square extractTreasure() {
        if (this.type == InputLineType.TREASURE) {
            String[] splitedInput = input.split(" - ");
            return new SquareBuilder().setHorizontalValue(Integer.parseInt(splitedInput[1])).setVerticalValue(Integer.parseInt(splitedInput[2])).setTreasureNumber(Integer.parseInt(splitedInput[3])).setIsTreasure(true).createSquare();
        }
        return null;
    }
}
