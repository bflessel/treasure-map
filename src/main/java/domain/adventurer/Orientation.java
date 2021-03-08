package domain.adventurer;

public enum Orientation {
    SOUTH(), EAST(), WEST(), NORTH();

    Orientation() {
    }


    public static Orientation valueOfOrDefault(String name) {
        return switch (name) {
            case "N" -> Orientation.NORTH;
            case "S" -> Orientation.SOUTH;
            case "E" -> Orientation.EAST;
            case "O" -> Orientation.WEST;
            default -> null;
        };
    }
}

