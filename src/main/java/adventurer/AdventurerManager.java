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

    public void moveAdventurerForward(TreasureMap map, Adventurer adventurer) throws AdventureWrongMoveException, AdventurerUnknownActionException {
        Square nextSquare = adventurer.getForwaredSquare();
        map.moveAdventurer(adventurer, nextSquare);
    }

    public void turnLeft(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        Adventurer turnedAdventurer = adventurer.turnLeft();
        map.updateAdventurer(turnedAdventurer);
    }

    public void turnRight(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        Adventurer turnedAdventurer = adventurer.turnRight();
        map.updateAdventurer(turnedAdventurer);
    }

    public void playActions(TreasureMap map, Adventurer ad) throws AdventureWrongMoveException, AdventurerUnknownActionException {
        Optional<Action> action = ad.getActions().stream().findFirst();

        if(action.isPresent()){
            playAction(action.get(), map, ad);
        }
    }

    private void playAction(Action action, TreasureMap map, Adventurer adventurer) throws AdventureWrongMoveException, AdventurerUnknownActionException {
        if(action == Action.MOVE_FORWARD){
            moveAdventurerForward(map,adventurer);
        }
    }
}
