package domain.adventurer;

import domain.coordinate.*;
import domain.square.*;
import exceptions.*;
import services.*;

import java.util.*;

public class Adventurer {
    private final String name;
    private Coordinate coordinate;
    private final Orientation orientation;
    private final String moveSet;
    private TreasureNumber treasureNumber;
    private Actions actions;

    public Adventurer(String name, Coordinate coordinate, Orientation orientation, String moveSet, TreasureNumber treasureNumber, Actions actions) throws AdventurerUnknownActionException {
        this.name = name;
        this.coordinate = coordinate;
        this.orientation = orientation;
        this.moveSet = moveSet;
        this.treasureNumber = treasureNumber != null ? treasureNumber : new TreasureNumber() ;
        this.actions = actions != null ? actions : AdventurerManager.getActions(moveSet);
    }



    public int getHorizontalValue() {
        return coordinate.getHorizontalValue();
    }

    public int getVerticalValue() {
        return coordinate.getVerticalValue();
    }

    public Coordinate getForwardSquare() {
        return coordinate.getForwardSquare(orientation);
    }

    @Override
    public String toString() {
        return "Adventurer{" +
                "name='" + name + '\'' +
                ", coordinate=" + coordinate +
                ", orientation=" + orientation +
                ", moveSet='" + moveSet + '\'' +
                ", treasureNumber=" + treasureNumber +
                ", actions=" + actions +
                '}';
    }

    public Adventurer getMovedAdventurer(Coordinate newCoordinate) throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setCoordinate(newCoordinate)
                .setMoveSet(this.moveSet)
                .setOrientation(this.orientation)
                .setName(this.name)
                .setActions(getNewActionList())
                .setTreasureNumber(this.treasureNumber)
                .createAdventurer();

    }

    private Actions getNewActionList() {
        return this.actions.getNewActionList();
    }

    public int getTreasureNumber() {
        return this.treasureNumber.giveNumber();
    }

    public void addTreasure() {
        treasureNumber.addTreasure();
    }

    public Adventurer turnLeft() throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setCoordinate(coordinate)
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
                .setCoordinate(coordinate)
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

    public Optional<Action> giveAction() {
        return actions.giveAction();
    }

    public Adventurer missTurn() throws AdventurerUnknownActionException {
        return new AdventurerBuilder()
                .setCoordinate(coordinate)
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

    public int giveActionNumber() {
        return actions.giveNumber();
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
        return Objects.equals(name, that.name) && Objects.equals(coordinate, that.coordinate) && orientation == that.orientation && Objects.equals(moveSet, that.moveSet) && Objects.equals(treasureNumber, that.treasureNumber) && Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinate, orientation, moveSet, treasureNumber, actions);
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
