package domain.adventurer;

public class AdventurerBuilder {
    private String name;
    private int horizontalValue;
    private int verticalValue;
    private Orientation orientation;
    private String moveSet;
    private int treasureNumber;

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

    public Adventurer createAdventurer() {
        return new Adventurer(name, horizontalValue, verticalValue, orientation, moveSet, treasureNumber);
    }

    public AdventurerBuilder setTreasureNumber(int treasureNumber) {
        this.treasureNumber = treasureNumber;
        return this;
    }
}