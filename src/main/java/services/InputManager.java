package services;

import java.io.*;
import java.nio.file.*;

public class InputManager {

    public static String getInputString(String filePath, String fileName) throws IOException {
        Path file = Path.of("", filePath).resolve(fileName);
        return Files.readString(file);    }

    public static Object getInputLine(String inputTest) {
        return null;
    }
}
