package domain.Square;

public class SquareBuilder {
    private int horizontalValue;
    private int verticalValue;
    private boolean isMountain;

    public SquareBuilder setHorizontalValue(int horizontalValue) {
        this.horizontalValue = horizontalValue;
        return this;
    }

    public SquareBuilder setVerticalValue(int verticalValue) {
        this.verticalValue = verticalValue;
        return this;
    }

    public SquareBuilder setIsMountain(boolean isMountain) {
        this.isMountain = isMountain;
        return this;
    }

    public Square createSquare() {
        return new Square(horizontalValue, verticalValue, isMountain);
    }
}