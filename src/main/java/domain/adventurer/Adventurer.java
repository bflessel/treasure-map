package domain.adventurer;

import domain.square.*;
import exceptions.*;
import services.*;

import java.util.*;

public class Adventurer {
    private final String name;
    private int horizontalValue;
    private int verticalValue;
    private final Orientation orientation;
    private final String moveSet;
    private int treasureNumber;
    private List<Action> actions;

    @Override
    public String toString() {
        return "Adventurer{" +
                "name='" + name + '\'' +
                ", horizontalValue=" + horizontalValue +
                ", verticalValue=" + verticalValue +
                ", orientation=" + orientation +
                ", moveSet='" + moveSet + '\'' +
                ", treasureNumber=" + treasureNumber +
                ", actions=" + actions +
                '}';
    }

    public Adventurer(String name, int horizontalValue, int verticalValue, Orientation orientation, String moveSet, int treasureNumber, List<Action> actions) throws AdventurerUnknownActionException {
        this.name = name;
        this.horizontalValue = horizontalValue;
        this.verticalValue = verticalValue;
        this.orientation = orientation;
        this.moveSet = moveSet;
        this.treasureNumber = treasureNumber;
        this.actions = actions != null ? actions : AdventurerManager.getActions(moveSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Adventurer that = (Adventurer) o;
        return horizontalValue == that.horizontalValue && verticalValue == that.verticalValue && treasureNumber == that.treasureNumber && Objects.equals(name, that.name) && orientation == that.orientation && Objects.equals(moveSet, that.moveSet) && Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, horizontalValue, verticalValue, orientation, moveSet, treasureNumber, actions);
    }

    public int getHorizontalValue() {
        return horizontalValue;
    }

    public int getVerticalValue() {
        return verticalValue;
    }

    public Square getForwardSquare() {
        SquareBuilder builder = new SquareBuilder();
        switch (this.orientation) {
            case SOUTH -> builder
                    .setHorizontalValue(this.horizontalValue)
                    .setVerticalValue(this.verticalValue + 1);
            case NORTH -> builder
                    .setHorizontalValue(this.horizontalValue)
                    .setVerticalValue(this.verticalValue - 1);
            case WEST -> builder
                    .setHorizontalValue(this.horizontalValue - 1)
                    .setVerticalValue(this.verticalValue);
            case EAST -> builder
                    .setHorizontalValue(this.horizontalValue + 1)
                    .setVerticalValue(this.verticalValue);
        }
        return builder.createSquare();

    }

    public Adventurer getMovedAdventurer(int newHorizontalValue, int newVerticalValue) throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setHorizontalValue(newHorizontalValue)
                .setVerticalValue(newVerticalValue)
                .setMoveSet(this.moveSet)
                .setOrientation(this.orientation)
                .setName(this.name)
                .setActions(getNewActionList())
                .setTreasureNumber(this.treasureNumber)
                .createAdventurer();

    }

    private List<Action> getNewActionList() {
        LinkedList<Action> newActions = new LinkedList<>(this.actions);
        newActions.removeFirst();
        return newActions;
    }

    public int getTreasureNumber() {
        return this.treasureNumber;
    }

    public void addTreasure() {
        treasureNumber++;
    }

    public Adventurer turnLeft() throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setHorizontalValue(horizontalValue)
                .setVerticalValue(verticalValue)
                .setMoveSet(moveSet)
                .setOrientation(getLeftOrientation())
                .setName(name)
                .setTreasureNumber(treasureNumber)
                .setActions(getNewActionList())
                .createAdventurer();

    }

    private Orientation getLeftOrientation() {
        Orientation newOrientation;
        switch (this.orientation) {
            case EAST -> newOrientation = Orientation.NORTH;
            case NORTH -> newOrientation = Orientation.WEST;
            case WEST -> newOrientation = Orientation.SOUTH;
            case SOUTH -> newOrientation = Orientation.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + this.orientation);
        }
        return newOrientation;
    }

    public Adventurer turnRight() throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setHorizontalValue(horizontalValue)
                .setVerticalValue(verticalValue)
                .setMoveSet(moveSet)
                .setOrientation(getRightOrientation())
                .setName(name)
                .setTreasureNumber(treasureNumber)
                .setActions(getNewActionList())
                .createAdventurer();

    }

    private Orientation getRightOrientation() {
        Orientation newOrientation;
        switch (this.orientation) {
            case EAST -> newOrientation = Orientation.SOUTH;
            case SOUTH -> newOrientation = Orientation.WEST;
            case WEST -> newOrientation = Orientation.NORTH;
            case NORTH -> newOrientation = Orientation.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + this.orientation);
        }
        return newOrientation;

    }

    public List<Action> getActions() {
        return this.actions;
    }

    public Adventurer missTurn() throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setHorizontalValue(horizontalValue)
                .setVerticalValue(verticalValue)
                .setMoveSet(moveSet)
                .setOrientation(orientation)
                .setName(name)
                .setTreasureNumber(treasureNumber)
                .setActions(getNewActionList())
                .createAdventurer();
    }

    public String getOrientation() {
        return orientation.getName();
    }

    public String getName() {
        return name;
    }
}
