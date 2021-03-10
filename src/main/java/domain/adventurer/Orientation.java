package domain.adventurer;

public enum Orientation {
    SOUTH("S"), EAST("E"), WEST("O"), NORTH("N");

    private final String name;

    Orientation(String n) {
        name = n;
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

    public String getName() {
        return name;
    }
}

