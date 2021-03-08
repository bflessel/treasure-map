package adventurer;

import domain.Square.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;

import java.util.*;

public class AdventurerManager
{
    public static List<Action> getActions(String inputs) throws AdventurerUnknownActionException {
        return getAction(inputs);

    }

    private static List<Action> getAction(String input) throws AdventurerUnknownActionException {
        return switch (input) {
            case "G" -> new LinkedList<>(Collections.singleton(Action.TURN_LEFT));
            case "D" -> new LinkedList<>(Collections.singleton(Action.TURN_RIGH));
            case "A" -> new LinkedList<>(Collections.singleton(Action.MOVE_FORWARD));
            default ->  throw new AdventurerUnknownActionException();

        };

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
