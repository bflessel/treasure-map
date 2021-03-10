package domain.coordinate;

public class CoordinateBuilder {
    private int horizontalValue;
    private int verticalValue;

    public CoordinateBuilder setHorizontalValue(int horizontalValue) {
        this.horizontalValue = horizontalValue;
        return this;
    }

    public CoordinateBuilder setVerticalValue(int verticalValue) {
        this.verticalValue = verticalValue;
        return this;
    }

    public Coordinate createCoordinate() {
        return new Coordinate(horizontalValue, verticalValue);
    }
}