import domain.inputline.*;
import domain.treasuremap.*;
import exceptions.*;
import services.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoMapProvidedException, AdventurerUnknownActionException, WrongAdventurerPlaceException, OutOfMapException, IOException {

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
        builder.append(OutputManager.getAdventurers(map));
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        byte[] strToBytes = builder.toString().getBytes();
        outputStream.write(strToBytes);
        System.out.println(builder.toString());
        outputStream.close();
    }
}
