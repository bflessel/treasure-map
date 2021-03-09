package services;

import domain.adventurer.*;
import domain.inputLine.*;
import domain.square.*;
import domain.treasureMap.*;

import java.util.*;
import java.util.stream.*;

public class OutputManager {
    public static final String SEPARATOR = " - ";

    public static String getMapOutputLine(List<InputLine> givenInputList) {
        Optional<InputLine> inputLine = givenInputList.stream().filter(i -> i.getType() == InputLineType.MAP).findFirst();
        String result = "No map provided";
        if (inputLine.isPresent()) {
            result = inputLine.get().getInput();
        }
        return result;
    }

    public static String getMountains(List<InputLine> givenInputList) {
        return givenInputList.stream().filter(i -> i.getType() == InputLineType.MOUNTAIN).map(InputLine::getInput).collect(Collectors.joining());
    }

    public static String getTreasures(TreasureMap map, List<InputLine> lines) {

        return lines.stream().filter(line -> line.getType() == InputLineType.TREASURE).map(InputLine::extractTreasure).map(square -> getTreasureInput(map.getSquare(square.getHorizontalValue(), square.getVerticalValue()))).collect(Collectors.joining());
    }

    private static String getTreasureInput(Square square) {
        StringBuilder builder = new StringBuilder();
        if (square.getTreasureNumber() > 0) {
            builder.append("T - ");
            appendCoordinates(square.getHorizontalValue(), square.getVerticalValue(), builder);
            builder.append(SEPARATOR).append(square.getTreasureNumber()).append("\n");

        }
        return builder.toString();
    }

    private static void appendCoordinates(int horizontalValue, int verticalValue, StringBuilder builder) {
        builder.append(horizontalValue)
                .append(SEPARATOR)
                .append(verticalValue);
    }

    public static String getAdventurers(TreasureMap map, List<InputLine> lines) {

        return map.getAdventurers().stream().map(adventurer -> getAdventurerInput(adventurer) + "\n").collect(Collectors.joining());
    }

    private static String getAdventurerInput(Adventurer adventurer) {
        StringBuilder builder = new StringBuilder();
        builder.append("A - ");
        builder.append(adventurer.getName());
        builder.append(SEPARATOR);
        appendCoordinates(adventurer.getHorizontalValue(), adventurer.getVerticalValue(), builder);
        builder.append(SEPARATOR)
                .append(adventurer.getOrientation())
                .append(SEPARATOR)
                .append(adventurer.getTreasureNumber());
        return builder.toString();
    }
}
