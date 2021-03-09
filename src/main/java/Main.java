import domain.inputLine.*;
import domain.treasureMap.*;
import exceptions.*;
import services.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoMapProvidedException, AdventurerUnknownActionException, WrongAdventurerPlaceException, OutOfMapException, IOException {
//        String inputTest = """
//                C - 3 - 4
//                M - 1 - 0
//                M - 2 - 1
//                T - 0 - 3 - 2
//                T - 1 - 3 - 3
//                A - Lara - 1 - 1 - S - AADADAGGA""";
//
        System.out.println(args[0]);
        String inputTest = InputManager.getInputString("./",args[0]);
        System.out.println(inputTest);
        InputManager inputManager = new InputManager();
        List<InputLine> inputList = inputManager.getAllInputLines(inputTest);
        TreasureMap map = inputManager.getMap(inputList);
        map.populate(inputList);
        new AdventurerManager().playAllActions(map);
        StringBuilder builder = new StringBuilder();
        builder.append(OutputManager.getMapOutputLine(inputList));
        builder.append(OutputManager.getMountains(inputList));
        builder.append(OutputManager.getTreasures(map, inputList));
        builder.append(OutputManager.getAdventurers(map, inputList));
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        byte[] strToBytes = builder.toString().getBytes();
        outputStream.write(strToBytes);
        System.out.println(builder.toString());
        outputStream.close();
    }
}
