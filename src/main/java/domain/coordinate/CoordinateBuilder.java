package domain.coordinate;

public class CoordinateBuilder {
    private int horizontalValue;
    private int verticallValue;

    public CoordinateBuilder setHorizontalValue(int horizontalValue) {
        this.horizontalValue = horizontalValue;
        return this;
    }

    public CoordinateBuilder setVerticalValue(int verticallValue) {
        this.verticallValue = verticallValue;
        return this;
    }

    public Coordinate createCoordinate() {
        return new Coordinate(horizontalValue, verticallValue);
    }
}