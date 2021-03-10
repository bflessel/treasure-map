package services;

import domain.inputline.*;
import domain.treasuremap.*;
import exceptions.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class InputManager {

    public static final String MAP_INDENTIFIER = "C - ";
    public static final String MOUNTAIN_IDENTIFIER = "M - ";
    public static final String TREASURE_IDENTIFIER = "T - ";
    public static final String ADVENTURER_IDENTIFIER = "A - ";
    public static final String LINE_SEPARATOR = "\n";

    public static String getInputString(String filePath, String fileName) throws IOException {
        Path file = Path.of("", filePath).resolve(fileName);
        return Files.readString(file);
    }

    public static InputLine getInputLine(String line) {
        if (line.startsWith(MAP_INDENTIFIER)) {
            return new InputLine(line, InputLineType.MAP);
        } else if (line.startsWith(MOUNTAIN_IDENTIFIER)) {
            return new InputLineBuilder().setInput(line).setType(InputLineType.MOUNTAIN).createInputLine();
        } else if (line.startsWith(TREASURE_IDENTIFIER)) {
            return new InputLineBuilder().setInput(line).setType(InputLineType.TREASURE).createInputLine();
        } else if (line.startsWith(ADVENTURER_IDENTIFIER)) {
            return new InputLineBuilder().setInput(line).setType(InputLineType.ADVENTURER).createInputLine();
        }
        return null;
    }


    public List<InputLine> getAllInputLines(String input) {
        String[] splitLines = input.split(LINE_SEPARATOR);
        return Arrays.stream(splitLines).map(InputManager::getInputLine).collect(Collectors.toList());
    }

    public TreasureMap getMap(List<InputLine> inputList) throws NoMapProvidedException {
        Optional<InputLine> optionalMap = inputList.stream().filter(line -> line.isCorrectLine(InputLineType.MAP)).findFirst();
        TreasureMap map;
        if (optionalMap.isPresent()) {
            map = optionalMap.get().extractMap();
        } else {
            throw new NoMapProvidedException();
        }
        return map;
    }
}
