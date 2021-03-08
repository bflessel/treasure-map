package adventurer;

import domain.Square.*;
import domain.adventurer.*;
import domain.treasureMap.*;
import exceptions.*;

import java.util.*;

public class AdventurerManager {
    public static List<Action> getActions(String inputs) throws AdventurerUnknownActionException {
        List<Action> actions = new LinkedList<>();
        for (Character input : inputs.toCharArray()) {
            actions.add(getAction(input));
        }
        return actions;

    }

    private static Action getAction(Character input) throws AdventurerUnknownActionException {
        return switch (input) {
            case 'G' -> Action.TURN_LEFT;
            case 'D' -> Action.TURN_RIGH;
            case 'A' -> Action.MOVE_FORWARD;
            default -> throw new AdventurerUnknownActionException();

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
