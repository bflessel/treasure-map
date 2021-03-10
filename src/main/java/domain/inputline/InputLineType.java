package domain.inputline;

public enum InputLineType {
    MOUNTAIN, TREASURE, ADVENTURER, MAP;

    public boolean isCorrectLine(InputLineType type) {
        return type == this;
    }
}
