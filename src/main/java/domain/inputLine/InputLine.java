package domain.inputLine;

import domain.square.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;

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
            String[] splitInput = input.split(SEPARATOR);
            return new TreasureMapBuilder().setHorizontalValue(Integer.parseInt(splitInput[1])).setVerticalValue(Integer.parseInt(splitInput[2])).createTreasureMap();
        }
        return null;
    }

    public Square extractMountain() {
        if (this.type == InputLineType.MOUNTAIN) {
            String[] splitInput = input.split(SEPARATOR);
            return new SquareBuilder().setHorizontalValue(Integer.parseInt(splitInput[1])).setVerticalValue(Integer.parseInt(splitInput[2])).setIsMountain(true).createSquare();
        }
        return null;    }

    public Square extractTreasure() {
        if (this.type == InputLineType.TREASURE) {
            String[] splitInput = input.split(" - ");
            return new SquareBuilder().setHorizontalValue(Integer.parseInt(splitInput[1])).setVerticalValue(Integer.parseInt(splitInput[2])).setTreasureNumber(Integer.parseInt(splitInput[3])).setIsTreasure(true).createSquare();
        }
        return null;
    }

    public Adventurer extractAdventurer() throws AdventurerUnknownActionException {
        if (this.type == InputLineType.ADVENTURER) {
            String[] splitInput = input.split(" - ");
            return new AdventurerBuilder()
                    .setName(splitInput[1])
                    .setHorizontalValue(Integer.parseInt(splitInput[2]))
                    .setVerticalValue(Integer.parseInt(splitInput[3]))
                    .setOrientation(Orientation.valueOfOrDefault(splitInput[4]))
                    .setMoveSet(splitInput[5])
                    .createAdventurer();
        }
        return null;

    }

    public InputLineType getType() {
        return type;
    }

    public String getInput() {
        return input+ "\n";
    }
}
