package domain.treasuremap;

public class TreasureMapBuilder {
    private int horizontalValue;
    private int verticalValue;

    public TreasureMapBuilder setHorizontalValue(int horizontalValue) {
        this.horizontalValue = horizontalValue;
        return this;
    }

    public TreasureMapBuilder setVerticalValue(int verticalValue) {
        this.verticalValue = verticalValue;
        return this;
    }

    public TreasureMap createTreasureMap() {
        return new TreasureMap(horizontalValue, verticalValue);
    }
}