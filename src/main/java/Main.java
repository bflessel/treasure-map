import domain.inputLine.*;
import domain.treasureMap.*;
import exceptions.*;
import services.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws NoMapProvidedException, AdventurerUnknownActionException, WrongAdventurerPlaceException, OutOfMapException {
        String inputTest = """
            C - 3 - 4
            M - 1 - 1
            T - 1 - 3 - 1
            A - Indiana - 0 - 1 - S - AADADA""";


        InputManager inputManager = new InputManager();
        List<InputLine> inputList = inputManager.getAllInputLines(inputTest);
        TreasureMap map = inputManager.getMap(inputList);
        map.populate(inputList);
        new AdventurerManager().playAllActions(map);

    }
}
