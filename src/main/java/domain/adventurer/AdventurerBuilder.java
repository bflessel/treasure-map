package domain.adventurer;

import domain.square.*;
import exceptions.*;

public class AdventurerBuilder {
    private String name;
    private int horizontalValue;
    private int verticalValue;
    private Orientation orientation;
    private String moveSet;
    private TreasureNumber treasureNumber;
    private Actions actions;

    public AdventurerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AdventurerBuilder setHorizontalValue(int horizontalValue) {
        this.horizontalValue = horizontalValue;
        return this;
    }

    public AdventurerBuilder setVerticalValue(int verticalValue) {
        this.verticalValue = verticalValue;
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
        return new Adventurer(name, horizontalValue, verticalValue, orientation, moveSet, treasureNumber,actions);
    }

    public AdventurerBuilder setTreasureNumber(TreasureNumber treasureNumber) {
        this.treasureNumber = treasureNumber;
        return this;
    }

    public AdventurerBuilder setActions(Actions actions) {
    this.actions = actions;
        return this;
    }
}