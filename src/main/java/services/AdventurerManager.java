package services;

import domain.adventurer.*;
import domain.coordinate.*;
import domain.treasureMap.*;
import exceptions.*;

import java.util.*;

public class AdventurerManager {
    public static Actions getActions(String inputs) throws AdventurerUnknownActionException {
        List<Action> actions = new LinkedList<>();
        for (Character input : inputs.toCharArray()) {
            actions.add(getAction(input));
        }
        return new Actions(actions);

    }

    private static Action getAction(Character input) throws AdventurerUnknownActionException {
        return switch (input) {
            case 'G' -> Action.TURN_LEFT;
            case 'D' -> Action.TURN_RIGHT;
            case 'A' -> Action.MOVE_FORWARD;
            default -> throw new AdventurerUnknownActionException();

        };

    }

    public static void moveAdventurerForward(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        Coordinate nextSquare = adventurer.getForwardSquare();
        map.moveAdventurer(adventurer, nextSquare);
    }

    public static void turnLeft(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        int index = map.getAdventurerIndex(adventurer);
        Adventurer turnedAdventurer = adventurer.turnLeft();
        map.updateAdventurer(turnedAdventurer, index);
    }

    public static void turnRight(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        int index = map.getAdventurerIndex(adventurer);
        Adventurer turnedAdventurer = adventurer.turnRight();
        map.updateAdventurer(turnedAdventurer, index);
    }

    public static void playActions(TreasureMap map, Adventurer ad) throws AdventurerUnknownActionException {
        Optional<Action> action = ad.giveAction();

        if (action.isPresent()) {
            playAction(action.get(), map, ad);
        }
    }

    private static void playAction(Action action, TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        switch (action) {
            case MOVE_FORWARD -> moveAdventurerForward(map, adventurer);
            case TURN_RIGHT -> turnRight(map, adventurer);
            case TURN_LEFT -> turnLeft(map, adventurer);
            default -> missTurn(map, adventurer);
        }
    }

    public static void missTurn(TreasureMap map, Adventurer adventurer) throws AdventurerUnknownActionException {
        int index = map.getAdventurerIndex(adventurer);
        Adventurer turnedAdventurer = adventurer.missTurn();
        map.updateAdventurer(turnedAdventurer, index);

    }

    public void playAllActions(TreasureMap map) throws AdventurerUnknownActionException {
        while (map.hasActions()) {
            map.playAdventurers();
        }


    }

}
