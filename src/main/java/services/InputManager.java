package services;

import domain.InputLine.*;

import java.io.*;
import java.nio.file.*;

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
        }
        return null;
    }


}
