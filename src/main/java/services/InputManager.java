package services;

import domain.InputLine.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class InputManager {

    public static String getInputString(String filePath, String fileName) throws IOException {
        Path file = Path.of("", filePath).resolve(fileName);
        return Files.readString(file);
    }

    public static InputLine getInputLine(String line) {
        if (line.startsWith("C - ")) {
            return new InputLine(line, InputLineType.MAP);
        } else if (line.startsWith("M - ")) {
            return new InputLineBuilder().setInput(line).setType(InputLineType.MOUNTAIN).createInputLine();
        } else if (line.startsWith("T - ")) {
            return new InputLineBuilder().setInput(line).setType(InputLineType.TREASURE).createInputLine();
        } else if (line.startsWith("A - ")) {
            return new InputLineBuilder().setInput(line).setType(InputLineType.ADVENTURER).createInputLine();
        }
        return null;
    }


    public List<InputLine> getAllInputLines(String input) {
        String[] splitLines = input.split("\n");
        List<InputLine>  lines = new ArrayList<>();
        for(String line : splitLines){
            lines.add(getInputLine(line));
        }
        return lines;    }
}
