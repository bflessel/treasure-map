package services;

import domain.inputline.*;
import domain.square.*;
import domain.treasuremap.*;
import exceptions.*;

import java.util.*;
import java.util.stream.*;

public class OutputManager {
    public static final String SEPARATOR = " - ";
    public static final String TREASURE_PREFIXE = "T - ";

    private OutputManager(){

    }
    public static String getMapOutputLine(List<InputLine> givenInputList) throws NoMapProvidedException {
        Optional<InputLine> inputLine = givenInputList.stream().filter(line -> line.isCorrectLine(InputLineType.MAP)).findFirst();
        String result;
        if (inputLine.isPresent()) {
            result = inputLine.get().getInput();
        } else throw new NoMapProvidedException();
        return result;
    }

    public static String getMountains(List<InputLine> givenInputList) {
        return givenInputList.stream().filter(line -> line.isCorrectLine(InputLineType.MOUNTAIN)).map(InputLine::getInput).collect(Collectors.joining());
    }

    public static String getTreasures(TreasureMap map, List<InputLine> lines) {

        return lines.stream().filter(line -> line.isCorrectLine(InputLineType.TREASURE)).map(InputLine::extractTreasure).map(square -> getTreasureInput(map.getSquare(square.getCoordinate()))).collect(Collectors.joining());
    }

    private static String getTreasureInput(Square square) {
        StringBuilder builder = new StringBuilder();
        if (square.getTreasureNumber() > 0) {
            builder.append(TREASURE_PREFIXE);
            appendCoordinates(square.getHorizontalValue(), square.getVerticalValue(), builder);
            builder.append(SEPARATOR).append(square.getTreasureNumber()).append("\n");

        }
        return builder.toString();
    }

    public static void appendCoordinates(int horizontalValue, int verticalValue, StringBuilder builder) {
        builder.append(horizontalValue)
                .append(SEPARATOR)
                .append(verticalValue);
    }

    public static String getAdventurers(TreasureMap map) {
        return map.getAdventurerInputs();
    }


}
