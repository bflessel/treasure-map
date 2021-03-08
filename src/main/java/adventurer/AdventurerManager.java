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
        int index = map.getAdventurerIndex(adventurer);
        Adventurer turnedAdventurer = adventurer.turnLeft();
        map.updateAdventurer(turnedAdventurer, index);
    }

    public void turnRight(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        int index = map.getAdventurerIndex(adventurer);
        Adventurer turnedAdventurer = adventurer.turnRight();
        map.updateAdventurer(turnedAdventurer, index);
    }

    public void playActions(TreasureMap map, Adventurer ad) throws AdventureWrongMoveException, AdventurerUnknownActionException {
        Optional<Action> action = ad.getActions().stream().findFirst();

        if (action.isPresent()) {
            playAction(action.get(), map, ad);
        }
    }

    private void playAction(Action action, TreasureMap map, Adventurer adventurer) throws AdventureWrongMoveException, AdventurerUnknownActionException {
        switch (action) {
            case MOVE_FORWARD -> moveAdventurerForward(map, adventurer);
            case TURN_RIGH -> turnRight(map, adventurer);
            case TURN_LEFT -> turnLeft(map, adventurer);
            default -> missTurn(map,adventurer);
        }
    }

    public void missTurn(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        int index = map.getAdventurerIndex(adventurer);
        Adventurer turnedAdventurer = adventurer.missTurn();
        map.updateAdventurer(turnedAdventurer, index);

    }

    public void playAllActions(TreasureMap map) throws AdventurerUnknownActionException, AdventureWrongMoveException {
        while (map.hasActions()) {
            for (Adventurer adventurer : map.getAdventurers()) {
                playActions(map, adventurer);
            }
        }


    }

}
