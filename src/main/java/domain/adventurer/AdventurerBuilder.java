package domain.adventurer;

import domain.coordinate.*;
import domain.square.*;
import exceptions.*;

public class AdventurerBuilder {
    private String name;
    private Orientation orientation;
    private String moveSet;
    private TreasureNumber treasureNumber;
    private Actions actions;
    private Coordinate coordinate;

    public AdventurerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AdventurerBuilder setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public AdventurerBuilder setMoveSet(String moveSet) {
        this.moveSet = moveSet;
        return this;
    }

    public Adventurer createAdventurer() throws AdventurerUnknownActionException {
        return new Adventurer(name,coordinate, orientation, moveSet, treasureNumber,actions);
    }

    public AdventurerBuilder setTreasureNumber(TreasureNumber treasureNumber) {
        this.treasureNumber = treasureNumber;
        return this;
    }

    public AdventurerBuilder setActions(Actions actions) {
    this.actions = actions;
        return this;
    }

    public AdventurerBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }
}