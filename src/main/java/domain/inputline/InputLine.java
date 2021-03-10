package domain.inputline;

import domain.adventurer.*;
import domain.coordinate.*;
import domain.square.*;
import domain.treasuremap.*;
import exceptions.*;

import java.util.*;

public class InputLine {
    public static final String SEPARATOR = " - ";
    private final String input;
    private final InputLineType type;

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
        if (this.type.isCorrectLine(InputLineType.MAP)) {
            String[] splitInput = input.split(SEPARATOR);
            return new TreasureMapBuilder().setHorizontalValue(Integer.parseInt(splitInput[1])).setVerticalValue(Integer.parseInt(splitInput[2])).createTreasureMap();
        }
        return null;
    }

    public Square extractMountain() {
        if (this.type.isCorrectLine(InputLineType.MOUNTAIN)) {
            String[] splitInput = input.split(SEPARATOR);
            return new SquareBuilder().setCoordinate(new CoordinateBuilder().setHorizontalValue(Integer.parseInt(splitInput[1])).setVerticalValue(Integer.parseInt(splitInput[2])).createCoordinate())
                    .setIsMountain(true).createSquare();
        }
        return null;
    }

    public Square extractTreasure() {
        if (this.type == InputLineType.TREASURE) {
            String[] splitInput = input.split(" - ");
            return new SquareBuilder().setCoordinate(
                    new CoordinateBuilder().setHorizontalValue(Integer.parseInt(splitInput[1])).setVerticalValue(Integer.parseInt(splitInput[2])).createCoordinate())
                    .setTreasureNumber(Integer.parseInt(splitInput[3])).setIsTreasure(true).createSquare();
        }
        return null;
    }

    public Adventurer extractAdventurer() throws AdventurerUnknownActionException {
        if (this.type == InputLineType.ADVENTURER) {
            String[] splitInput = input.split(" - ");

            return new AdventurerBuilder()
                    .setName(splitInput[1])
                    .setCoordinate(new CoordinateBuilder().setHorizontalValue(Integer.parseInt(splitInput[2])).setVerticalValue(Integer.parseInt(splitInput[3])).createCoordinate())
                    .setOrientation(Orientation.valueOfOrDefault(splitInput[4]))
                    .setMoveSet(splitInput[5])
                    .setTreasureNumber(new TreasureNumber(0))
                    .createAdventurer();
        }
        return null;

    }

    public String getInput() {
        return input + "\n";
    }

    public boolean isCorrectLine(InputLineType testedType) {
        return testedType.isCorrectLine(this.type);
    }

    public Optional<Square> extractMountainOrTreasure() {
        return switch (type) {
            case MOUNTAIN -> Optional.ofNullable(extractMountain());
            case TREASURE -> Optional.ofNullable(extractTreasure());
            default -> Optional.empty();
        };
    }
}
