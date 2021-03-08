package adventurer;

import domain.Square.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;

import java.util.*;

public class AdventurerManager
{
    public static List<Action> getActions(String inputs) {
        return getAction(inputs);

    }

    private static List<Action> getAction(String input) {
        return new  LinkedList<>(Collections.singleton(Action.MOVE_FORWARD));
    }

    public void moveAdventurerForward(TreasureMap map, Adventurer adventurer) throws AdventureWrongMoveException {
        Square nextSquare = adventurer.getForwaredSquare();
        map.moveAdventurer(adventurer, nextSquare);
    }

    public void turnLeft(TreasureMap map, Adventurer adventurer) {
        Adventurer turnedAdventurer = adventurer.turnLeft();
        map.updateAdventurer(turnedAdventurer);
    }

    public void turnRight(TreasureMap map, Adventurer adventurer) {
        Adventurer turnedAdventurer = adventurer.turnRight();
        map.updateAdventurer(turnedAdventurer);
    }
}
