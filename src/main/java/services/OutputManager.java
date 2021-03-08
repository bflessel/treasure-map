package services;

import domain.inputLine.*;

import java.util.*;

public class OutputManager {
    public static String getMapOutputLine(List<InputLine> givenInputList) {
        Optional<InputLine> inputLine = givenInputList.stream().filter(i -> i.getType() == InputLineType.MAP).findFirst();
        String result = "No map provided";
        if (inputLine.isPresent()) {
            result = inputLine.get().getInput();
        }
        return result;
    }
}
