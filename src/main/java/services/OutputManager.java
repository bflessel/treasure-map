package services;

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

    public static List<String> getMountains(List<InputLine> givenInputList) {
        return givenInputList.stream().filter(i -> i.getType() == InputLineType.MOUNTAIN).map(InputLine::getInput).collect(Collectors.toList());
    }

    public static List<String> getTreasures(TreasureMap map, List<InputLine> lines) {
        List<String> treasureOutputs = new ArrayList<>();
        for (InputLine line : lines) {
            if (line.getType() == InputLineType.TREASURE) {
                Square square = line.extractTreasure();
                treasureOutputs.add(getTreasureInput(map.getSquare(square.getHorizontalValue(), square.getVerticalValue())));
            }
        }

        return treasureOutputs;
    }

    private static String getTreasureInput(Square square) {
        StringBuilder builder = new StringBuilder();
        builder.append("T - ");
        appendCoordinates(square, builder);
        builder.append(SEPARATOR);
        builder.append(square.getTreasureNumber());
        return builder.toString();
    }

    private static void appendCoordinates(Square square, StringBuilder builder) {
        builder.append(square.getHorizontalValue())
                .append(SEPARATOR)
                .append(square.getVerticalValue());
    }

}
